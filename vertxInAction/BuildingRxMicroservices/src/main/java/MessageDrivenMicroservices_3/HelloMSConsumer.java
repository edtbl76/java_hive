package MessageDrivenMicroservices_3;

import io.reactivex.Single;
import io.vertx.core.Launcher;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.eventbus.EventBus;
import io.vertx.reactivex.core.eventbus.Message;

public class HelloMSConsumer extends AbstractVerticle {

    public static void main(String[] args) {
        Launcher.executeCommand("run", HelloMSConsumer.class.getName(), "--cluster");
    }

    @Override
    public void start() {
        // 1. wrap the event bus work into a requestHandler
        vertx.createHttpServer()
                .requestHandler(
                        // Callback is handling the EventBus pieces.
                        httpServerRequest -> {
                            EventBus bus = vertx.eventBus();

                            // EventBus is generating requests and mapping them to a json message
                            Single<JsonObject> s1 = bus
                                    .<JsonObject>rxRequest("hello", "Batman").map(Message::body);
                            Single<JsonObject> s2 = bus
                                    .<JsonObject>rxRequest("hello", "Robin").map(Message::body);
                            Single
                                    // zip is mooshing it all together into a single observable object.
                                    .zip(s1, s2, (bruce, dick) ->
                                            new JsonObject()
                                                    .put("Batman", bruce.getString("message")
                                                            + " from " + bruce.getString("served-by"))
                                                    .put("Robin", dick.getString("message")
                                                            + " from " + dick.getString("served-by"))
                                    )
                                    // This allows our observables to be observed.
                                    .subscribe(
                                            result -> httpServerRequest.response().end(result.encodePrettily()),
                                            throwable -> {
                                                throwable.printStackTrace();
                                                httpServerRequest.response()
                                                        .setStatusCode(500).end(throwable.getMessage());
                                            }
                                    );
                        }).listen(8082);
    }
}
