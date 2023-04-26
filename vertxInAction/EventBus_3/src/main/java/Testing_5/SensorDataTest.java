package Testing_5;

import HeatSensorExample_2.SensorData;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

// VertxExtension is required for Testing
@ExtendWith(VertxExtension.class)
public class SensorDataTest {

    // JUnit 5 uses dependency Injection, The test Context is used to query the VertxExtensions so it knows what
    // to inject when the parameters are declared.
    @Test
    void testAverage(Vertx vertx, VertxTestContext vertxTestContext) {
        EventBus bus = vertx.eventBus();
        // if the deployment fails, succeeding() will make the test fail.
        vertx.deployVerticle(new SensorData(), vertxTestContext.succeeding(id -> {
            // We publish 2 temperatures to ensure that the class is working.
            bus.publish("sensor.updates", new JsonObject()
                    .put("id", "a").put("temp", 20.0D));
            bus.publish("sensor.updates", new JsonObject()
                    .put("id", "b").put("temp", 22.0D));

            // assertions mus tbe wrapped IN a callback that is passed to the verify() method of the test context
            // This is the only way the EXTENSIONS can catch failures.
            bus.request("sensor.average", "", vertxTestContext.succeeding(
                    reply -> vertxTestContext.verify(() -> {
                        JsonObject json = (JsonObject) reply.body();
                        assertEquals(21.0d, (double) json.getDouble("average"));
                        // completeNow() makes the test pass.
                        // The logic is "if you get this far, you either missed a test case, or its working"
                        vertxTestContext.completeNow();
                    })));
        }));
    }
}
