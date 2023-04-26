package ClusteringAndDistrEventBus_3;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstInstance {
    private static final Logger logger = LoggerFactory.getLogger(FirstInstance.class);

    public static void main(String[] args) {

        // starting Vert.x cluster is asynchronous options
        Vertx.clusteredVertx(new VertxOptions(), vertxAsyncResult -> {
            if (vertxAsyncResult.succeeded()) {
                logger.info("First instance started");

                // upon success, we store the vertx instance
                Vertx vertx = vertxAsyncResult.result();
                vertx.deployVerticle("HeatSensorExample_2.HeatSensor", new DeploymentOptions().setInstances(4));
                vertx.deployVerticle("HeatSensorExample_2.HttpServer");
            } else {
                logger.error("Failed to start", vertxAsyncResult.cause());
            }
        });
    }
}
