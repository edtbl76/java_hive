package HeatSensorsRevisited_1;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

@ProxyGen
@VertxGen
public interface SensorDataService {

    /*
        The Handler<AsyncResult<T>> is commonly used for callbacks in Vert.x APIs.
            - T is usually a Json type.
     */
    static SensorDataService create(Vertx vertx) {
        return new SensorDataServiceImpl(vertx);
    }

    static SensorDataService createProxy(Vertx vertx, String address) {
        return new SensorDataServiceVertxEBProxy(vertx,address);
    }

    // Asynchronously ask for sensor value
    void valueFor(String sensorId, Handler<AsyncResult<JsonObject>> handler);

    // Asynchronously ask for average.
    void average(Handler<AsyncResult<JsonObject>> handler);
}
