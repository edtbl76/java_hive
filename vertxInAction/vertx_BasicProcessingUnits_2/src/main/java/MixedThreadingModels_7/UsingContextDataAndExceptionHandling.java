package MixedThreadingModels_7;

import io.vertx.core.Context;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsingContextDataAndExceptionHandling {

    private static final Logger logger = LoggerFactory.getLogger(UsingContextDataAndExceptionHandling.class);

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        // this is going to create a vertx context.
        Context context = vertx.getOrCreateContext();

        context.put("tuber", "poTAHto");

        context.exceptionHandler(throwable -> {
            if ("potayto".equalsIgnoreCase(throwable.getMessage())) {
                logger.info("Got a _PO-TAY-TO_ exception");
            } else {
                logger.error("Some other breakage!", throwable);
            }
        });

        // This triggers our handled exception
        context.runOnContext(aVoid -> {
            throw new RuntimeException("potayto");
        });

        // This gets what we want
        context.runOnContext(aVoid -> logger.info("Tuber = {}", (String) context.get("tuber")));

        // This will return null
        context.runOnContext(aVoid -> logger.info("NotExist = {}", (String) context.get("NotExist")));

        // This blows up, because we are giving it an exception we didn't handle
        context.runOnContext(aVoid -> {
            throw new RuntimeException("BUSTED!");
        });

    }
}
