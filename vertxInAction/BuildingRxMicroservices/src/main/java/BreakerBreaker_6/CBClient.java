package BreakerBreaker_6;

import io.vertx.circuitbreaker.CircuitBreakerOptions;
import io.vertx.core.Launcher;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.circuitbreaker.CircuitBreaker;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.RxHelper;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import io.vertx.reactivex.ext.web.client.WebClient;

public class CBClient extends AbstractVerticle {

    public static void main(String[] args) {
        Launcher.executeCommand("run", CBClient.class.getName());
    }

    @Override
    public void start() {
        CircuitBreakerOptions options = new CircuitBreakerOptions()
                .setMaxFailures(5)
                .setFallbackOnFailure(true)
                .setTimeout(2000)
                .setResetTimeout(5000);

        CircuitBreaker rxBreaker = CircuitBreaker.create("my-breaker", vertx, options)
                .openHandler(v -> System.out.println("CB Opened"))
                .closeHandler(v -> System.out.println("CB closed"));

        WebClient client = WebClient.create(vertx);
        rxBreaker.rxExecuteCommandWithFallback(
                promise -> {
                    client.get(8083, "localhost", "/himike")
                    .rxSend()
                    .subscribeOn(RxHelper.scheduler(vertx))
                    .map(HttpResponse::bodyAsJsonObject)
                    .subscribe(
                            entries -> {
                                System.out.println("My Message: " + entries);
                                promise.tryComplete();
                            },
                            throwable -> new JsonObject().put("message", throwable.getMessage())
                    );
                    // This segment  is for NO FALLBACK.
//                    .subscribe(
//                            promise::tryComplete,
//                            throwable -> System.out.println("Promise Fallback" + throwable.getMessage())
//                    );
                }, throwable -> {
                    throwable.printStackTrace();
                    System.out.println("Execute Fallback " + throwable.getMessage());
                    return new JsonObject().put("message", throwable.getMessage());
                }
        ).subscribe();
    }
}
