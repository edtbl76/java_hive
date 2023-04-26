package Verticles_1;

import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CreateContextWithNoVerticle {
    private static final Logger logger = LoggerFactory.getLogger(CreateContextWithNoVerticle.class);
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        /*
            Each one of these gets its own event loop thread.
            If we call getOrCreateContext() w/o creating a verticle, then it has to create a new Context instance
                - therefore it gets a separate thread.

            If we call this INSIDE a verticle, then it will return the context instance associated w/ that verticle.
         */
        vertx.getOrCreateContext().runOnContext(aVoid -> logger.info("ABC"));
        vertx.getOrCreateContext().runOnContext(aVoid -> logger.info("123"));
    }
}
