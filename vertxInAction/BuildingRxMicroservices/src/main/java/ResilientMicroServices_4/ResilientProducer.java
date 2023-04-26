package ResilientMicroServices_4;

import io.vertx.core.Launcher;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;

public class ResilientProducer extends AbstractVerticle {

    public static void main(String[] args) {
        Launcher.executeCommand("run", ResilientProducer.class.getName(), "--cluster");
    }

    @Override
    public void start() {
        vertx.eventBus().<String>consumer("hello", stringMessage -> {
            double chaosMonkey = Math.random();
            JsonObject json = new JsonObject().put("served-by", this.toString());

            if (chaosMonkey < 0.6) {
                // Working Order
                if (stringMessage.body().isEmpty())
                    stringMessage.reply(json.put("message", "hello"));
                else
                    stringMessage.reply(json.put("message", "hello " + stringMessage.body()));
            } else if (chaosMonkey < 0.9) {
                // Failure Mode
                stringMessage.fail(500, "Shit Happens");
            } else {
                // lost in space.
                System.out.println("Nope. Not gonna reply!");
            }
        });
    }
}
