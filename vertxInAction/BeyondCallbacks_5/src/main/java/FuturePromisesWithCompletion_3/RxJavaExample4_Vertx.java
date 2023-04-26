package FuturePromisesWithCompletion_3;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.vertx.core.Context;
import io.vertx.reactivex.RxHelper;
import io.vertx.reactivex.core.AbstractVerticle;

import java.util.concurrent.TimeUnit;

public class RxJavaExample4_Vertx extends AbstractVerticle {

    // rxStart notifiles of verticle deployment success using a Completable instead of a Future!
    @Override
    public Completable rxStart() {

        Observable
                // Scheduler enforces vert.x threading model
                .interval(1, TimeUnit.SECONDS, RxHelper.scheduler((Context) vertx))
                .subscribe(n -> System.out.println("tick"));

        return vertx.createHttpServer()
                .requestHandler(httpServerRequest -> httpServerRequest.response().end("Ok!"))
                // This is the RxJava variant of listen(port, callback)
                .rxListen(8080)
                // returns a Completable from a Single.
                .ignoreElement();

    }
}
