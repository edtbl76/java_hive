package HeatSensorsRevisited_1;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ServiceException;
import io.vertx.serviceproxy.ServiceExceptionMessageCodec;

public class SensorDataServiceVertxEBProxy implements SensorDataService {

    private Vertx _vertx;
    private String _address;
    private DeliveryOptions _options;
    private boolean closed;

    public SensorDataServiceVertxEBProxy(Vertx vertx, String address) {
        this(vertx, address, null);
    }

    public SensorDataServiceVertxEBProxy(Vertx vertx, String address, DeliveryOptions options) {
        this._vertx = vertx;
        this._address = address;
        this._options = options;
        try {
            this._vertx.eventBus().registerDefaultCodec(ServiceException.class, new ServiceExceptionMessageCodec());
        } catch (IllegalStateException ex) {}
    }

    @Override
    public void valueFor(String sensorId, Handler<AsyncResult<JsonObject>> handler) {
        if (closed) {
            handler.handle(Future.failedFuture(new IllegalStateException("Proxy closed.")));
            return;
        }
        JsonObject _json = new JsonObject();
        _json.put("sensorId", sensorId);

        DeliveryOptions _deliveryOptions = (_options != null) ? new DeliveryOptions(_options) : new DeliveryOptions();
        _deliveryOptions.addHeader("action", "valueFor");
        _vertx.eventBus().<JsonObject>request(_address, _json, _deliveryOptions, messageAsyncResult -> {
            if (messageAsyncResult.failed()) {
                handler.handle(Future.failedFuture(messageAsyncResult.cause()));
            } else {
                handler.handle(Future.succeededFuture(messageAsyncResult.result().body()));
            }
        });
    }

    @Override
    public void average(Handler<AsyncResult<JsonObject>> handler) {
        if(closed) {
            handler.handle(Future.failedFuture(new IllegalStateException("Closed Proxy")));
            return;
        }
        JsonObject _json = new JsonObject();

        DeliveryOptions _deliveryOptions = (_options != null) ? new DeliveryOptions(_options) : new DeliveryOptions();
        _deliveryOptions.addHeader("action", "average");
        _vertx.eventBus().<JsonObject>request(_address, _json, _deliveryOptions, messageAsyncResult -> {
            if (messageAsyncResult.failed()) {
                handler.handle(Future.failedFuture(messageAsyncResult.cause()));
            } else {
                handler.handle(Future.succeededFuture(messageAsyncResult.result().body()));
            }
        });
    }
}
