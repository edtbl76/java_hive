package AsyncNotification_3;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class AsyncVerticle extends AbstractVerticle {

    /*
    Good Example of a Non-Blocking Async start life-cycle method
    starting HTTP server
     */

    /*
        FUTURE is a type Void, because we are only interested in "completion"
        - there is no "value" to return or carry on.
     */
    @Override
    //public void start(Future<Void> startFuture) throws Exception {  // pre 3.8 works. Deprecated in 3.8
    public void start(Promise<Void> startFuture) throws Exception {   // 3.8 and later
        vertx.createHttpServer()
                .requestHandler(request -> request.response().end("Ok"))
                // The listen() polymorphic variant supports an async result that allows knowing if
                // success is true or false. (kind of a cheap way of not having to use a boolean)
                .listen(8080, httpServerAsyncResult -> {
                    if (httpServerAsyncResult.succeeded())
                        startFuture.complete();                             // quietly succeed.
                    else
                        startFuture.fail(httpServerAsyncResult.cause());   // return the cause of failure
                });

    }
}
