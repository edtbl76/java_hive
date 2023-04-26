package BlockingCode_6;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/*
     A worker verticle is single-threaded (just like Event-Loop Verticles)
     - however the thread may not always be the same (EventLoop verticles always use the same thread!!!!!!!)
 */
public class WorkerVerticle extends AbstractVerticle {
    private final Logger logger = LoggerFactory.getLogger(WorkerVerticle.class);

    @Override
    public void start() throws Exception {
        vertx.setPeriodic(10_000, id -> {
            try {
                logger.info("Sleepin...");
                Thread.sleep(8000);                 // We are blocking, but getting no warning!!!
                logger.info("OkOk, I'm up!");
            } catch (InterruptedException ex) {
                logger.error("Crap", ex);
            }
        });
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        DeploymentOptions opts = new DeploymentOptions()
                .setInstances(2)
                // This sets the "Worker Verticle Flag"
                .setWorker(true);
        vertx.deployVerticle("BlockingCode_6.WorkerVerticle", opts);
    }
}
