package ResilientMicroServices_4;

import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.RxHelper;
import io.vertx.reactivex.core.eventbus.EventBus;
import io.vertx.reactivex.core.eventbus.Message;

import java.util.concurrent.TimeUnit;

public class ResilientConsumer extends AbstractVerticle {

    public static void main(String[] args) {

    }

    @Override
    public void start() {
        vertx.createHttpServer().requestHandler(httpServerRequest -> {
            EventBus bus = vertx.eventBus();

            /*
                Most of this is the same other than the resiliency efforts

                - this starts the same way as the MessageDriven examples
                    - the event bus writes a request to the other microservice (at address "hello", w/ message "Tom")

                - we immediately deviate by  using
                    subscribeOn(). This is really just a thread scheduler that determines what thread each invocation is
                    going to be assigned to.

                    THIS IS VERY IMPORTANT.
                    - If this were left to defaults, then RxJava would select threads from the RXJAVA default thread pool,
                    which BREAKS the Vert.x Thread model.

                    - This is the purpose of the RxHelper.scheduler(). RxHelper assigns callbacks to the Vert.x Event Loop,
                    keeping consistent w/ the vert.x multireactor pattern (and threading model)

                - timeout is basically a good faith effort to ensure that a response is recvd (when expected) in a
                timely manner.
                - the retry is to resubmit to ensure it wasn't just a "bump"
                    NOTE: retries like this can inject failure very fast into distributed systems if they aren't capped
                    or  managed. if the delay in response is due to load, then retries are just going to make the problem
                    worse.
                - the last part is the transform of the payload into the Json body.
             */
            Single<JsonObject> s1 = bus
                    .<JsonObject>rxRequest("hello", "Tom")
                    .subscribeOn(RxHelper.scheduler(vertx))
                    .timeout(3, TimeUnit.SECONDS)
                    .retry()
                    .map(Message::body);

            Single<JsonObject> s2 = bus
                    .<JsonObject>rxRequest("hello", "Jerry")
                    .subscribeOn(RxHelper.scheduler(vertx))
                    .timeout(3, TimeUnit.SECONDS)
                    .retry()
                    .map(Message::body);

            Single.zip(s1, s2, (cat, mouse) -> new JsonObject()
                    .put("Tom", cat.getString("message") + " from " + cat.getString("served-by"))
                    .put("Jerry", mouse.getString("message") + " from " + mouse.getString("served-by"))
        ).subscribe(
                result -> httpServerRequest.response().end(result.encodePrettily()),
                throwable -> {
                    throwable.printStackTrace();
                    httpServerRequest.response().setStatusCode(500).end(throwable.getMessage());
                });


        }).listen(8082);
    }
}
