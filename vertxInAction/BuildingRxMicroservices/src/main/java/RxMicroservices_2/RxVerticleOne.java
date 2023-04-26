package RxMicroservices_2;

import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.http.HttpServer;

public class RxVerticleOne extends AbstractVerticle {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(String.valueOf(RxVerticleOne.class.getName()));
    }

    @Override
    public void start() throws Exception {

        HttpServer server = vertx.createHttpServer();

        // get stream of request as an Observable (only works for the RX version of HttpServer!)
        server.requestStream().toObservable()
                .subscribe(httpServerRequest -> {
                   httpServerRequest.response().end(
                           "Hello from " + Thread.currentThread().getName()
                   );
                   /*
                        Server is started using rxListen
                            - returns a Single of HttpServer.
                            - nee to subscribe in order to trigger the action.
                    */
                   server
                           .rxListen(8080)
                           .subscribe();
                });
    }
}
