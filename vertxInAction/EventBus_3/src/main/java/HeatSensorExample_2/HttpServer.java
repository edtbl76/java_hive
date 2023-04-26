package HeatSensorExample_2;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.TimeoutStream;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;

public class HttpServer extends AbstractVerticle {
    @Override
    public void start() throws Exception {
       vertx.createHttpServer()
               .requestHandler(this::handler)
                // set default port
               .listen(config().getInteger("port", 8080));
    }

    private void handler(HttpServerRequest request) {
        if ("/".equals(request.path())) {
            // sendFile() allows streaming the content of ANY LOCAL FILE to the client.
            // (Automatically closed connection when finished)
            request.response().sendFile("index.html");
        } else if ("/sse".equals(request.path())) {
            // SSE = Server-Sent-Events will use the /sse resource. and a method is provided for handling these
            sse(request);
        } else {
            // all else 404
            request.response().setStatusCode(404);
        }
    }

    private void sse(HttpServerRequest request) {
        HttpServerResponse response = request.response();
        response

                // text/event-stream MIME type is recommended for server-sent events
                .putHeader("Content-Type", "text/event-stream")

                // since this is a live stream, we want to prevent browsers/proxies from caching it.
                .putHeader("Cache-Control", "no-cache")
                .setChunked(true);

        /*
            We call the consumer WITHOUT a handler because we need an object to cancel the subscription when the client
            disconnects.
         */
        MessageConsumer<JsonObject> consumer = vertx.eventBus().consumer("sensor.updates");
        consumer.handler(jsonObjectMessage -> {

            // Sending an event block is sometimes as easy as sending text.
            response.write("event: update\n");
            response.write("data: " + jsonObjectMessage.body().encode() + "\n\n");
        });

        // We update the average once per second, so we need a periodic timer.
        TimeoutStream ticks = vertx.periodicStream(1000);

        // Here we are using an expression Lambda (crazy!)
        // In addition to the lambda, we are using the eventBus().request() method which replaces the deprecated
        // send() method. It is preferred to use request() when we are passing in a callback when a response is expected.
        // This is a part of the REQUEST-AND-REPLY model of the Vert.x EventBus.
        // The callback is asynchronous, as it might also fail.
        ticks.handler(aLong -> vertx.eventBus().<JsonObject>request("sensor.average", "", messageAsyncResult -> {
            if (messageAsyncResult.succeeded()) {
                response.write("event: average\n");
                response.write("data: " + messageAsyncResult.result().body().encode() + "\n\n");
            }
        }));

        /*
        Cleanup work.
        When the client disconnects
            - deregister the message concumer
            - cancel the periodic timer for averaging requests.
         */
        response.endHandler(aVoid -> {
            consumer.unregister();
            ticks.cancel();
        });
    }
}
