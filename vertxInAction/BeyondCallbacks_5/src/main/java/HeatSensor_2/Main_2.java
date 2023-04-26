package HeatSensor_2;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class Main_2 {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle("HeatSensor_2.HeatSensor",
                new DeploymentOptions().setConfig(new JsonObject().put("http.port", 3000)));
        vertx.deployVerticle("HeatSensor_2.HeatSensor",
                new DeploymentOptions().setConfig(new JsonObject().put("http.port", 3001)));
        vertx.deployVerticle("HeatSensor_2.HeatSensor",
                new DeploymentOptions().setConfig(new JsonObject().put("http.port", 3002)));

        vertx.deployVerticle("HeatSensor_2.SnapshotService");
        vertx.deployVerticle("HeatSensor_2.CollectorService");
    }
}
