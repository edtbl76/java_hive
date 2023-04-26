package BlockingCode_6;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Offload extends AbstractVerticle {
    private final Logger logger = LoggerFactory.getLogger(Offload.class);

    @Override
    public void start() {
        vertx.setPeriodic(5000, id -> {
            logger.info("Tick");

            /*
                ExecuteBlocking Takes 2 params.
                    - the code to run
                    - the callback when it has completed.
             */
            vertx.executeBlocking(this::blockingCode, this::resultHandler);
        });
    }

    /*
        Blocking Code takes a future object. Remember the Future<> interface was deprecated in favor of Promise<>
        - this is being used to eventually pass the result (the callback)

        the future object must either complete() or fail().
            - this marks the end of the blocking operation, and it provides the "result" for the resultHandler.
     */
    private void blockingCode(Promise<String> future) {
        logger.info("Running blocking code");
        try {
            Thread.sleep(4000);
            logger.info("Done!");
            future.complete("Ok!");
        } catch (InterruptedException ex) {
            future.fail(ex);
        }
    }

    /*
        the callback speaks "EventLoop", so it is basically just another asynchronous result on the event loop, like
        a normal verticle workload.
     */
    private void resultHandler(AsyncResult<String> asyncResult) {
        if (asyncResult.succeeded()) {
            logger.info("Blocking Code result: {}", asyncResult.result());
        } else {
            logger.error("Crap", asyncResult.cause());
        }
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new Offload());

    }

}
