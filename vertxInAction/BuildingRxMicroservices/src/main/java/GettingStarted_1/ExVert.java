package GettingStarted_1;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;

public class ExVert extends AbstractVerticle {

    public static void main(String[] args) {
        Launcher.executeCommand("run", ExVert.class.getName());
    }

    @Override
    public void start() {
        /*
            Vertx instance creates a Netty server.
            - set up a request handler to handle any incoming requests that come in on 8080.
            - Any requests result in displaying "Hello"
         */
        vertx.createHttpServer()
                .requestHandler(httpServerRequest -> httpServerRequest.response().end("hello")
                ).listen(8080);
    }
}
