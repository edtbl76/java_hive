package PassingConfigData_5;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

public class SampleVerticle extends AbstractVerticle {
    private final Logger logger = LoggerFactory.getLogger(SampleVerticle.class);

    @Override
    public void start() {
        /* config() returns the JsonObject configuration instance
            - this particular accessor is for Integer types.
            - the 2nd parameter is a default value in the event that there is no 'n' key in the config entry
            - (This defends against NPE)
        */
        logger.info("n = {}", config().getInteger("n", -1));
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        IntStream.range(0, 4).forEach(value -> {

            // Here we pack the Json configuration object w/ Integers.
            JsonObject conf = new JsonObject().put("n", value);
            DeploymentOptions opts = new DeploymentOptions()

                    // Deployment Options are a more flexible deployment mechanism, allowing us to set the config values
                    .setConfig(conf)

                    // this allows multiple instances to be deployed at once.
                    .setInstances(value);
            // NOTE: since multiple instances are deployed at once... we must use the fully qualified class name (FQCN)
            // If there is only one instance we can use the FQCN or new.
            vertx.deployVerticle("PassingConfigData_5.SampleVerticle", opts);
        });
    }
}
