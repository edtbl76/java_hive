package HeatSensorsRevisited_1.reactivex;

import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.unit.Async;

@io.vertx.lang.rx.RxGen(HeatSensorsRevisited_1.SensorDataService.class)
public class SensorDataService {

    @Override
    public String toString() {
        return delegate.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        SensorDataService that = (SensorDataService) obj;
        return delegate.equals(that.delegate);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    public static final io.vertx.lang.rx.TypeArg<SensorDataService> __TYPE_ARG
            = new io.vertx.lang.rx.TypeArg<>(o -> new SensorDataService((HeatSensorsRevisited_1.SensorDataService) o),
            SensorDataService::getDelegate
    );

    private final HeatSensorsRevisited_1.SensorDataService delegate;

    public SensorDataService(HeatSensorsRevisited_1.SensorDataService delegate) {
        this.delegate = delegate;
    }

    public HeatSensorsRevisited_1.SensorDataService getDelegate() {
        return delegate;
    }

    public static SensorDataService create(io.vertx.reactivex.core.Vertx vertx) {
        SensorDataService ret =
                SensorDataService.newInstance(HeatSensorsRevisited_1.SensorDataService.create(vertx.getDelegate()));
        return ret;
    }

    public static SensorDataService createProxy(io.vertx.reactivex.core.Vertx vertx, String address) {
        SensorDataService ret =
                SensorDataService.newInstance(
                        HeatSensorsRevisited_1.SensorDataService.createProxy(vertx.getDelegate(), address));
        return ret;
    }

    public void valueFor(String sensorId, Handler<AsyncResult<JsonObject>> handler) {
        delegate.valueFor(sensorId, handler);
    }

    public Single<JsonObject> rxValueFor(String sensorId) {
        return io.vertx.reactivex.impl.AsyncResultSingle.toSingle(
                asyncResultHandler -> valueFor(sensorId, asyncResultHandler));
    }

    public void average(Handler<AsyncResult<JsonObject>> handler) {
        delegate.average(handler);
    }

    public Single<JsonObject> rxAverage() {
        return io.vertx.reactivex.impl.AsyncResultSingle.toSingle(this::average);
    }

    public static SensorDataService newInstance(HeatSensorsRevisited_1.SensorDataService arg) {
        return arg != null ? new SensorDataService(arg) : null;
    }
}
