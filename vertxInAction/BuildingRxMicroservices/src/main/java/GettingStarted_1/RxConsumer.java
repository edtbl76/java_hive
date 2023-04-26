package GettingStarted_1;

import io.reactivex.Single;
import io.vertx.core.Launcher;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.client.HttpRequest;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import io.vertx.reactivex.ext.web.client.WebClient;
import io.vertx.reactivex.ext.web.codec.BodyCodec;

public class RxConsumer  extends AbstractVerticle {

    public static void main(String[] args) {
        Launcher.executeCommand("run", RxConsumer.class.getName());
    }

    private WebClient client;
    @Override
    public void start() {
        // Client + Router
        client = WebClient.create(vertx);
        Router router =  Router.router(vertx);

        router.get("/").handler(this::invoke);

        vertx.createHttpServer().requestHandler(router).listen(8081);
    }

    private void invoke(RoutingContext rc) {
        /*
            You probably noticed by now that we switched to RxJava.
            This is where the usefulness pays off.

            In order to multiple concurrent (and independent!) requests, we need to try to
            leverage RxJava.

            Nothing has changed with the proocess.
            Here we are using the client to invoke the "other" microservice by encoding specific paths as Json.
         */
        HttpRequest<JsonObject> rq1  = client
                .get(8080,  "localhost", "/Zan")
                .as(BodyCodec.jsonObject());
        HttpRequest<JsonObject> rq2  = client
                .get(8080,  "localhost", "/Jayna")
                .as(BodyCodec.jsonObject());


        /*
            Here, we take each request and emit the payload.
                rxSend actually creates an Observable (which is the input of map() operator)
                - map() is going to transform the input into a JsonObject.

                The RETURN value (output) of each request is a Single Observable (of JsonObject type).
         */
        Single<JsonObject> s1 = rq1.rxSend().map(HttpResponse::body);
        Single<JsonObject> s2 = rq2.rxSend().map(HttpResponse::body);

        /*
            This takes a set of Single(s) and marries them together. (like a dating app, but with less sketchiness)
            - This IS A BOTTLENECK.
            - zip waits for ALL of the Singles to have their values before processing the callback.

            - The callback is just the creation of a singular JsonObject() containing the data we processed from the
            individual requests.

            subscribe() is the GLUE
                - In order to see the emitted items being pumped out by an Observable (Producer!!!), we have to
                subscribe()

                In this case, a Single has two possible callbacks.
                    1.) what to do when i'm done  (in this case encode it as json)
                    2.) what to do when things break. (error handling!)
         */
        Single.zip(s1, s2, (zan, jayna) -> new JsonObject()
                .put("Zan", zan.getString("message"))
                .put("Jayna", jayna.getString("message"))
        ).subscribe(
                result -> rc.response().end(result.encodePrettily()),
                error -> {
                    error.printStackTrace();
                    rc.response().setStatusCode(500).end(error.getMessage());
                }
        );
    }
}

/*
    How are we doing on our "Reactive-O-Meter"

    1. We have two microservices.
    - they are BOTH independent (Autonomy!)
        - they could be deployed/updated apart from each other.
        - they have lightweight interaction (HTTP)
    2. THe WebClient allows the interaction to be asynchronous.

    BUT!
    - they aren't reactive.

    Problem 1:
        - Web Client is bound specifically to THIS microservice.
        - if we go down, the other service can't be called to recover.
        - we have the same problem trying to scale out to handle increased load.

    Problem 2:
        - We are invoking microservices via direct (HARD CODED) URL, which makes elasticity almost impossible.
 */
