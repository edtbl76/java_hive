package GettingStarted_1;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class Vert3CleaningItUp extends AbstractVerticle {

    public static void main(String[] args) {
        Launcher.executeCommand("run", Vert3CleaningItUp.class.getName());
    }

    @Override
    public void start() {
        Router router = Router.router(vertx);
        // The only change in the start() override is to make the code more readable
        // (hint hint!) by using method expression to hide the implementation of the callback.
        router.get("/").handler(this::invoke);
        router.get("/:name").handler(this::invoke);

        vertx.createHttpServer().requestHandler(router).listen(8080);
    }

    private void invoke(RoutingContext rc) {

        /*
            The first step is to recreate (and simplify) the logic surrounding how we handle the path parameters for
            the registered routes.
         */
        String message = "hello";
        if (rc.pathParam("name") != null)
            message += " " + rc.pathParam("name");

        /*
            Now, rather than returning raw text, we'll format our response into Json,
            and then adjust our response() to handle Json as a response format.
         */
        JsonObject json = new JsonObject().put("message", message);
        rc.response()
                .putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .end(json.encode());
    }
}

/*
    Where is the client???
    - Technically at this phase... it is your browser, curl, postman.. whatever. We haven't built one.
 */
