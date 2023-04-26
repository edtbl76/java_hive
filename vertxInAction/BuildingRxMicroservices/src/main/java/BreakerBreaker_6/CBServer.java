package BreakerBreaker_6;

import io.vertx.core.Launcher;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;

public class CBServer extends AbstractVerticle {

    public static void main(String[] args) {
        Launcher.executeCommand("run", CBServer.class.getName());
    }

    @Override
    public void start() {
        Router router = Router.router(vertx);
        router.get("/").handler(this::invokeServer);
        router.get("/:name").handler(this::invokeServer);

        vertx.createHttpServer().requestHandler(router).listen(8083);
    }

    private void invokeServer(RoutingContext rc) {
        String message = "Hello";
        if (rc.pathParam("name") != null)
            message += ", " + rc.pathParam("name");

        JsonObject json = new JsonObject().put("message", message);
        rc.response()
                .putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .end(json.encode());


    }
}
