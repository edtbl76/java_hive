package GettingStarted_1;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.ext.web.Router;

public class RouterVert extends AbstractVerticle {

    public static void main(String[] args) {
        Launcher.executeCommand("run", RouterVert.class.getName());
    }

    @Override
    public void start() {
        /*
            This instantiates a Route and registers to routes. (one of which reads path parameters for use the
            callback)
         */
        Router router = Router.router(vertx);
        router.get("/").handler(
                routingContext -> routingContext.response().end("hello"));
        router.get("/:name").handler(
                routingContext -> routingContext.response().end("hello " + routingContext.pathParam("name")));

        /*
            The only change here is that our Http Server instance is using our router instance to determine
            how to handle the incoming requests.
         */
        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8080);


    }
}
