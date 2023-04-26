package JukeBoxApplication_3;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.NetSocket;
import io.vertx.core.parsetools.RecordParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetController extends AbstractVerticle  {
    private final Logger logger = LoggerFactory.getLogger(NetController.class);

    // ----- //


    @Override
    public void start() throws Exception {
        logger.info("Start");
        vertx.createNetServer()
                .connectHandler(this::handleClient)
                .listen(3000);
    }

    private void handleClient(NetSocket socket) {
        logger.info("New Connection");
        RecordParser.newDelimited("\n", socket)
                .handler(buffer -> handleBuffer(socket, buffer))
                .endHandler(aVoid -> logger.info("Connection ended"));
    }

    // ----- //

    private void handleBuffer(NetSocket socket, Buffer buffer) {
        String command = buffer.toString();
        switch (command) {
            case "/list":
                listCommand(socket);
                break;
            case "/play":
                vertx.eventBus().send("jukebox.play", "");
                break;
            case "/pause":
                vertx.eventBus().send("jukebox.pause", "");
                break;
            default:
                if (command.startsWith("/schedule")) {
                    schedule(command);
                } else {
                    socket.write("Unknown command\n");
                }
        }
    }

    // ----- //

    private void schedule(String command) {
        String track = command.substring(10);
        JsonObject json = new JsonObject().put("file", track);
        vertx.eventBus().send("jukebox.schedule", json);
    }

    private void listCommand(NetSocket socket) {
        vertx.eventBus().request("jukebox.list", "", messageAsyncResult -> {
            if (messageAsyncResult.succeeded()) {
                JsonObject data = (JsonObject) messageAsyncResult.result().body();
                data.getJsonArray("files")
                        .stream().forEach(name -> socket.write(name + "\n"));
            } else {
                logger.error("/list error", messageAsyncResult.cause());
            }
        });
    }
}
