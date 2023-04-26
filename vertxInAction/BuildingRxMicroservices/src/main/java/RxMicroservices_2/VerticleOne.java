package RxMicroservices_2;

import io.vertx.core.AbstractVerticle;

public class VerticleOne extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        // Create an Http Server
        vertx.createHttpServer()
                // the requestHandler is call'd for each incoming req.
                // print() name of thread
                .requestHandler(httpServerRequest -> {
                    httpServerRequest.response().end(
                            "Hello from " + Thread.currentThread().getName());
                }).listen(8080);
    }
}
