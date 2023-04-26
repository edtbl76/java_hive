package ClusteringAndDistrEventBus_3;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SecondInstance {
    private static final Logger logger = LoggerFactory.getLogger(SecondInstance.class);

    public static void main(String[] args) {
        Vertx.clusteredVertx(new VertxOptions(), vertxAsyncResult -> {
            if(vertxAsyncResult.succeeded()) {
                logger.info("Second Instance started");
                Vertx vertx = vertxAsyncResult.result();
                vertx.deployVerticle("HeatSensorExample_2.HeatSensor", new DeploymentOptions().setInstances(4));
                vertx.deployVerticle("HeatSensorExample_2.Listener");
                vertx.deployVerticle("HeatSensorExample_2.SensorData");

                // Use a different port so we can start both instances on the same host
                JsonObject conf = new JsonObject().put("port", 8081);
                vertx.deployVerticle("HeatSensorExample_2.HttpServer", new DeploymentOptions().setConfig(conf));
            } else {
                logger.error("Failed to start", vertxAsyncResult.cause());
            }
        });
    }
}
