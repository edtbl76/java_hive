package JukeBoxApplication_3;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class JukeBox extends AbstractVerticle  {
    private final Logger logger = LoggerFactory.getLogger(JukeBox.class);

    @Override
    public void start() throws Exception {
        logger.info("Start");

        EventBus bus = vertx.eventBus();

        // consumers.
        bus.consumer("jukebox.list", this::list);
        bus.consumer("jukebox.schedule", this::schedule);
        bus.consumer("jukebox.play", this::play);
        bus.consumer("jukebox.pause", this::pause);

        // create HttpServer (Request Handler)
        vertx.createHttpServer()
                .requestHandler(this::httpHandler)
                .listen(8080);

        // stream audio in chunks
        vertx.setPeriodic(100, this::streamAudioChunk);
    }

    // ---- //

    // Manage Player State
    private enum State { PLAYING, PAUSED }
    private State currentMode = State.PAUSED;

    // Create Storage For Our PlayList.
    private final Queue<String> playlist = new ArrayDeque<>();

    // --- //

    private void list(Message<?> request) {

        // Here we are going through the file system and hunting for mp3s. each time we find one, we hit the callback
        vertx.fileSystem().readDir("tracks", ".*mp3$", listAsyncResult -> {

            /*
                - Result set is List of Strings, we convert it to a Stream of Strings.
                - each String is mapped to a File object, and then we get that File's name. (back to a Stream of Strings)
                - we collect those and case them to a List.

                - pack the files into a JsonArray w/ a key of "files" in a json Object.

                - reply w/ that JsonObject.
             */
            if (listAsyncResult.succeeded()) {
                List<String> files = listAsyncResult.result()
                        .stream()
                        .map(File::new)
                        .map(File::getName)
                        .collect(Collectors.toList());
                JsonObject json = new JsonObject().put("files", new JsonArray(files));
                request.reply(json);
            } else {
                // bail out w/ the cause.
                logger.error("readDir() failed", listAsyncResult.cause());
                request.fail(500, listAsyncResult.cause().getMessage());
            }
        });
    }

    // ------ //

    /*
        Play/Pause methods are simple log and state setters.
     */
    private void play(Message<?> request) {
        logger.info("Play");
        currentMode = State.PLAYING;
    }

    private void pause(Message<?> request) {
        logger.info("Pause");
        currentMode = State.PAUSED;
    }

    private void schedule(Message<JsonObject> request) {
        String file = request.body().getString("file");
        logger.info("Scheduling {}", file);

        // This allows us to automatically resume playing when no track was playing and we schedule a new one.
        if (playlist.isEmpty() && currentMode == State.PAUSED) {
            currentMode = State.PLAYING;
        }
        // ArrayDeque.offer() is pretty much a "put at the end"
        playlist.offer(file);
    }

    // ------ //

    private void httpHandler(HttpServerRequest request) {
        // This logs the Http Handler
        logger.info("{} '{}' {}", request.method(), request.path(), request.remoteAddress());

        // if our path is empty, we hand the request to the audio stream manager.
        if("/".equals(request.path())) {
            openAudioStream(request);
            return;
        }

        // if it starts w/ download, we'll download the mp3
        if (request.path().startsWith("/download/")) {
            // This is a special defensive coding technique that prevents assholes from reading files from other
            // directories.
            String sanitized = request.path().substring(10).replaceAll("/", "");
            download(sanitized, request);
            return;
        }

        // Otherwise 404 that shit, and call end() to kill the stream.
        request.response().setStatusCode(404).end();
    }

    // ----- //

    // This tracks current HTTP Response streams.
    private final Set<HttpServerResponse> streamers = new HashSet<>();

    private void openAudioStream(HttpServerRequest request) {
        logger.info("New Streamer");
        HttpServerResponse response = request.response()
                .putHeader("Content-Type", "audio/mpeg")
                // its a good idea to Chunk streams, because length is an unknown.
                .setChunked(true);
        streamers.add(response);

        // Once the stream exits, it is no longer tracked.
        response.endHandler(aVoid -> {
            streamers.remove(response);
            logger.info("A streamer left");
        });
    }

    // ------ //


    private void download(String path, HttpServerRequest request) {
        String file = "tracks/" + path;
        // Blocking time is marginal on local file systems. This is a performance enhancement to avoid a callback level
        // NOTE: do NOT doing this on networked file systems, as the blocking time can be significant.
        if (!vertx.fileSystem().existsBlocking(file)) {
            request.response().setStatusCode(404).end();
            return;
        }

        /*
            Vert.x File ReadStream.
         */
        OpenOptions opts = new OpenOptions().setRead(true);
        vertx.fileSystem().open(file, opts, asyncFileAsyncResult -> {
            if (asyncFileAsyncResult.succeeded()) {
                // Put the Business Logic somewhere else.
                downloadFile(asyncFileAsyncResult.result(), request);
            } else {
                logger.error("Read Failed.", asyncFileAsyncResult.cause());
                request.response().setStatusCode(404).end();
            }
        });
    }

    // Here it is! This is a great example of implementing BackPressure Management
    private void downloadFile(AsyncFile file, HttpServerRequest request) {
        // This is the response to a request.
        HttpServerResponse response = request.response();
        response.setStatusCode(200)
                .putHeader("Content-Type", "audio/mpeg")
                .setChunked(true);

        // This is the handler
        file.handler(buffer -> {
            response.write(buffer);
            // If the queue is full, then pause() (BackPressure!)
            // Drain the Drain (w/ resume callback)
            if (response.writeQueueFull()) {
                file.pause();
                response.drainHandler(aVoid -> file.resume());
            }
        });

        // Streams have to be explicitly ended or they go on and on and on.
        file.endHandler(aVoid -> response.end());
    }

    private void downloadFilePipe(AsyncFile file, HttpServerRequest request) {
        HttpServerResponse response = request.response();
        response.setStatusCode(200)
                .putHeader("Content-Type", "audio/mpeg")
                .setChunked(true);
        file.pipeTo(response);
    }

    // ---------- //

    private AsyncFile currentFile;
    private long positionInFile;

    private void streamAudioChunk(long id) {
        if (currentMode == State.PAUSED) {
            return;
        }

        if (currentFile == null && playlist.isEmpty()) {
            currentMode = State.PAUSED;
            return;
        }

        if (currentFile == null) {
            openNextFile();
        }

        currentFile.read(Buffer.buffer(4096), 0, positionInFile, 4096, bufferAsyncResult -> {
            if (bufferAsyncResult.succeeded()) {
                processReadBuffer(bufferAsyncResult.result());
            } else {
                logger.error("Read Failed", bufferAsyncResult.cause());
                closeCurrentFile();
            }
        });
    }

    // ---------- //

    private void openNextFile() {
        logger.info("Opening {} ", playlist.peek());
        OpenOptions opts = new OpenOptions().setRead(true);
        currentFile = vertx.fileSystem()
                .openBlocking("tracks/" + playlist.poll(), opts);
        positionInFile = 0;
    }

    private void closeCurrentFile() {
        logger.info("Closing file");
        positionInFile = 0;
        currentFile.close();
        currentFile = null;
    }

    // ----- //

    private void processReadBuffer(Buffer buffer) {
        logger.info("Read {} bytes from pos {}", buffer.length(), positionInFile);
        positionInFile += buffer.length();
        if (buffer.length() == 0) {
            closeCurrentFile();
            return;
        }

        streamers.forEach(httpServerResponse -> {
            if (!httpServerResponse.writeQueueFull()) {
                httpServerResponse.write(buffer.copy());
            }
        });
    }
}
