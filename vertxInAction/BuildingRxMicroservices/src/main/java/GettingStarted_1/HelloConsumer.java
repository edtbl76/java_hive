package GettingStarted_1;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;

public class HelloConsumer extends AbstractVerticle {

    public static void main(String[] args) {
        Launcher.executeCommand("run", HelloConsumer.class.getName());
    }

    /*
        Heah's ya fakkin' client kehd!
     */
    private WebClient client;

    @Override
    public void start() {
        // Setup the client and the Router
        client = WebClient.create(vertx);
        Router router = Router.router(vertx);

        /*
            Where did the path parameter go??
         */
        router.get("/").handler(this::invoke);

        /*
            Http Server for the Consumer (different port.. because its gotta be up
            at the same time as the other Verticle
        */
        vertx.createHttpServer().requestHandler(router).listen(8081);

    }

    private void invoke(RoutingContext rc) {

        /*
            This invokes the other microservice. w/ a specific path, and encode it as Json
         */
        HttpRequest<JsonObject> request = client
                .get(8080, "localhost", "/vertx")
                .as(BodyCodec.jsonObject());

        /*
            THis actually emits the request....
                - the callback is how we handle the response.
                    - if shit is broken, we fail and dump the cause()
                    - otherwise encode that as json.
         */
        request.send(httpResponseAsyncResult -> {
            if (httpResponseAsyncResult.failed())
                rc.fail(httpResponseAsyncResult.cause());
            else
                rc.response().end(httpResponseAsyncResult.result().body().encode());
        });
    }
}

/*
    Some knowledge being dropped:
    - We no longer communicate to the "other" microservice.
    - We send a request to THIS Consumer @ 8081 which invokes the "other" microservice at localhost:8080/vertx.

    -
 */