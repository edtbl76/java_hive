package DeployingVerticles_4;

import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Deployer extends AbstractVerticle {
    private final Logger logger = LoggerFactory.getLogger(Deployer.class);

    @Override
    public void start() {
        long delay = 1000;
        for (int i = 0; i < 50; i++) {
            // Here, we deploy every second
            vertx.setTimer(delay, id -> deploy());
            delay = delay + 1000;
        }
    }

    private void deploy() {
        // Deploying Verticles is an Asynchronous Operation.
        vertx.deployVerticle(new EmptyVerticle(), stringAsyncResult -> {
            if (stringAsyncResult.succeeded()) {
                String id = stringAsyncResult.result();
                logger.info("Successfully deployed {}", id);

                // We will undeploy every 5 seconds.
                vertx.setTimer(5000, aLong -> undeployLater(id));
            } else {
                logger.error("Deployment Error", stringAsyncResult.cause());
            }
        });
    }

    // undeploy and deploy have consistent patterns/interfaces, for ease of use.
    private void undeployLater(String id) {
        vertx.undeploy(id, voidAsyncResult -> {
            if(voidAsyncResult.succeeded()) {
                logger.info("{} undeployed", id);
            } else {
                logger.error("{} failed to undeploy", id);
            }
        });
    }
}
