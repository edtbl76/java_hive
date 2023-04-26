package HeatSensorExample_2;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.stream.Collectors;

public class SensorData extends AbstractVerticle {
    // This stores the latest measurement of each sensor by the ID
    private final HashMap<String, Double> lastValues = new HashMap<>();

    @Override
    public void start() {
        // We're only going to declare 2 event bus destination handlers
        EventBus bus = vertx.eventBus();
        bus.consumer("sensor.updates", this::update);
        bus.consumer("sensor.average", this::average);
    }

    private void update(Message<JsonObject> message) {
        JsonObject json = message.body();
        lastValues.put(json.getString("id"), json.getDouble("temp"));
    }

    // There is no incoming message for average requests, so the incoming doc is going to be empty.
    private void average(Message<JsonObject> message) {
        double avg = lastValues.values().stream().collect(Collectors.averagingDouble(Double::doubleValue));
        JsonObject json = new JsonObject().put("average", avg);
        // this reply occurs via the reply() method.
        message.reply(json);
    }
}
