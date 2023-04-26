package Verticles_1;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloVerticle extends AbstractVerticle {

    private final Logger logger = LoggerFactory.getLogger(HelloVerticle.class);
    private long counter = 1;

    @Override
    public void start() throws Exception {

        // Handler 1: Periodic (Poller)
        vertx.setPeriodic(5000, id -> logger.info("tick"));

        // Handler 2: processes http requests.
        vertx.createHttpServer()
                .requestHandler(request -> {
                    logger.info("Request #{} from {}", counter++, request.remoteAddress().host());
                    request.response().end("Hello!");
                }).listen(8080);
        logger.info("Open http://localhost:8080/");
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();                // "Global Vertx instance"
        vertx.deployVerticle(new HelloVerticle());  // easiest way to deploy Vertx.
    }
}
