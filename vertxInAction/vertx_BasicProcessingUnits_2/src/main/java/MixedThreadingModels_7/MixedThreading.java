package MixedThreadingModels_7;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/*
This demonstrates an example of using NON Vert.x threads (i.e. Plain Old Java Thread POJT lol.. this isn't going to catch on)
WITH
Vert.x threads.

It also demonstrates having to use the damn CountDownLatch in order to manage the blocking code.
 */
public class MixedThreading extends AbstractVerticle {
    private final Logger logger = LoggerFactory.getLogger(MixedThreading.class);

    @Override
    public void start() throws Exception {
        Context context = vertx.getOrCreateContext();
        new Thread(() -> {
            try {
                run(context);
            } catch (InterruptedException ex) {
                logger.error("Aw Crap", ex.getMessage());
            }
            // Start a plain old Java thread.
        }).start();
    }

    private void run(Context context) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        logger.info("I'm living in a non-Vertx thread");
        // this ensures that we run code on the event-loop thread.
        context.runOnContext(v-> {
            logger.info("I'm IN the vert.x event-loop");
            vertx.setTimer(1000, id -> {
                logger.info("This is the final countdown!");
                latch.countDown();
            });
        });
        logger.info("Waiting for CountDownLatch");
        latch.await();
        logger.info("Bye, now!");
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MixedThreading());
    }
}
