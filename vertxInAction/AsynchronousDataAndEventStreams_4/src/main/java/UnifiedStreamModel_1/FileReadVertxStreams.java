package UnifiedStreamModel_1;

import io.vertx.core.Vertx;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;

public class FileReadVertxStreams {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        // OpenOptions is for setting params.
        OpenOptions opts = new OpenOptions().setRead(true);

        // file open is asynchronous in vertx.
        vertx.fileSystem().open("pom.xml", opts, asyncFileAsyncResult -> {
           if (asyncFileAsyncResult.succeeded()) {
               AsyncFile file = asyncFileAsyncResult.result();  // Vert.x interface for
               file.handler(System.out::println)    // callback for new buffer data
                       .exceptionHandler(Throwable::printStackTrace)    // callback for bad things
                       .endHandler(aVoid -> {                           // callback for stream ending.
                           System.out.println("\n--- DONE");
                           vertx.close();
                       });
           } else {
               asyncFileAsyncResult.cause().printStackTrace();
           }
        });
    }
}
