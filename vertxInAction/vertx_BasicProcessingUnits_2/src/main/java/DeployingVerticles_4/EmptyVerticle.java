package DeployingVerticles_4;

import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
    This is just an Empty Verticle we will deploy.
    - it just logs start/stop events.
 */
public class EmptyVerticle extends AbstractVerticle {
    private Logger logger = LoggerFactory.getLogger(EmptyVerticle.class);

    @Override
    public void start() throws Exception {
        logger.info("Start");
    }

    @Override
    public void stop() throws Exception {
        logger.info("Stop");
    }
}
