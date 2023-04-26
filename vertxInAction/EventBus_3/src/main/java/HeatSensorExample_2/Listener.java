package HeatSensorExample_2;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;


public class Listener extends AbstractVerticle {
    private final Logger logger = LoggerFactory.getLogger(Listener.class);

    // cute little way to constrain precision to a specific format.
    private final DecimalFormat format = new DecimalFormat("#.##");

    @Override
    public void start() {
        EventBus bus = vertx.eventBus();

        // creates a consumer method to subscribe methods. (callback is used to handle the event-bus messages)
        bus.<JsonObject>consumer("sensor.updates", jsonObjectMessage -> {
            // This gets the message body from the payload.
            JsonObject body = jsonObjectMessage.body();
            String id = body.getString("id");
            String temp = format.format(body.getDouble("temp"));

            // Just log the results.
            logger.info("{} reports a temperature ~{}C", id, temp);
        });
    }
}
