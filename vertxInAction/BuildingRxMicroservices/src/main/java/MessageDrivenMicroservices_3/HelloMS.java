package MessageDrivenMicroservices_3;

import io.vertx.core.Launcher;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;

public class HelloMS extends AbstractVerticle {

    public static void main(String[] args) {
        Launcher.executeCommand("run", HelloMS.class.getName(), "--cluster");
    }

    @Override
    public void start() {
        /*
            1. get eventbus from vertx instance
            2. register a consumer() at address "hello".
            3. callback does the following:
                - creates a JsonObject.
                - depending on the state of the incoming message...
                    - if a body is not present, we write a default body into the Json Object.
                    - otherwise, we inject the message body back into the response.


         */
        vertx.eventBus().<String>consumer("hello", stringMessage -> {
            JsonObject json = new JsonObject().put("served-by", this.toString());

            if (stringMessage.body().isEmpty())
                stringMessage.reply(json.put("message",  "hello"));
            else
                stringMessage.reply(json.put("message", "hello " + stringMessage.body()));
        });
    }
}
