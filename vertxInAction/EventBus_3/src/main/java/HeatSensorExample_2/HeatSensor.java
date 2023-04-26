package HeatSensorExample_2;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

import java.util.Random;
import java.util.UUID;

public class HeatSensor extends AbstractVerticle {
    private final Random random = new Random();

    // Generate random id for SensorID
    private final String id = UUID.randomUUID().toString();
    private double temp = 21.0;

    @Override
    public void start() {
        scheduleNextUpdate();
    }

    // Updates scheduled based on a 1-6 second randomixed timer.
    private void scheduleNextUpdate() {
        vertx.setTimer(random.nextInt(5000) + 1000, this::update);
    }

    private void update(long tid) {
        temp = temp + (delta() / 10);
        JsonObject payload = new JsonObject()
                .put("id", id)
                .put("temp", temp);
        // Publish is the Pub/Sub comm method. This will send the updates to the sensor.updates destination.
        // Subscribers will pull it from that destination.
        vertx.eventBus().publish("sensor.updates", payload);

        // next update is scheduled.
        scheduleNextUpdate();
    }

    // computes a random slight modification to create small adjustments to current temperature.
    private double delta() {
        return (random.nextDouble() > 0) ? random.nextGaussian() : -random.nextGaussian();
    }
}
