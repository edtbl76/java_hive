package HeatSensorExample_2;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

import java.util.Arrays;

public class Main_2 {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        // Start 4 sensors
        vertx.deployVerticle("HeatSensorExample_2.HeatSensor", new DeploymentOptions().setInstances(4));
        Arrays.asList(
                "HeatSensorExample_2.Listener",
                "HeatSensorExample_2.SensorData",
                "HeatSensorExample_2.HttpServer").forEach(vertx::deployVerticle);

    }
}
