package ResilientMicroServices_4;

import io.reactivex.Single;
import io.vertx.core.Launcher;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.RxHelper;
import io.vertx.reactivex.core.eventbus.EventBus;
import io.vertx.reactivex.core.eventbus.Message;

import java.util.concurrent.TimeUnit;

public class ResilientConsumer2 extends AbstractVerticle {
    /*
        Another version of the TimeOut Resilient Consumer, but w/ an error message when a timeout occurs

     */

    public static void main(String[] args) {
        Launcher.executeCommand("run", ResilientConsumer2.class.getName(), "--cluster");
    }

    @Override
    public void start() {
        vertx.createHttpServer().requestHandler(httpServerRequest -> {
            EventBus bus = vertx.eventBus();

            Single<JsonObject> s1 = bus
                    .<JsonObject>rxRequest("hello", "RetryForever")
                    .subscribeOn(RxHelper.scheduler(vertx))
                    .timeout(3, TimeUnit.SECONDS)
                    .retry()
                    .map(Message::body);

            Single<JsonObject> s2 = bus
                    .<JsonObject>rxRequest("hello", "ErrorOnTimeout")
                    .subscribeOn(RxHelper.scheduler(vertx))
                    .timeout(3, TimeUnit.SECONDS)
                    .map(Message::body)
                    .onErrorReturn(throwable -> new JsonObject().put("message", "Times up!"))
                    // Note: We are limiting the number of retries so we don't beat the piss out of the server.
                    .retry(5);



            Single.zip(s1, s2, (r, e) -> new JsonObject()
                    .put("Tom", r.getString("message") + " from " + r.getString("served-by"))
                    .put("Jerry", e.getString("message") + " from " + e.getString("served-by"))
            ).subscribe(
                    result -> httpServerRequest.response().end(result.encodePrettily()),
                    throwable -> {
                        throwable.printStackTrace();
                        httpServerRequest.response().setStatusCode(500).end(throwable.getMessage());
                    });


        }).listen(8082);

    }
}
