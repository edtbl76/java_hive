package HeatSensorsRevisited_1;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.HelperUtils;
import io.vertx.serviceproxy.ProxyHandler;
import io.vertx.serviceproxy.ServiceException;
import io.vertx.serviceproxy.ServiceExceptionMessageCodec;

public class SensorDataServiceVertxProxyHandler extends ProxyHandler {

    public static final long DEFAULT_CONNECTION_TIMEOUT = 5 * 60;
    private final Vertx vertx;
    private final SensorDataService service;
    private final long timerId;
    private long lastAccessed;
    private final long timeoutSeconds;

    public SensorDataServiceVertxProxyHandler(Vertx vertx, SensorDataService service) {
        this(vertx, service, DEFAULT_CONNECTION_TIMEOUT);
    }

    public SensorDataServiceVertxProxyHandler(Vertx vertx, SensorDataService service, long timeoutInSeconds) {
        this(vertx, service, true, timeoutInSeconds);
    }

    public SensorDataServiceVertxProxyHandler(Vertx vertx, SensorDataService service, boolean topLevel,
                                              long timeoutSeconds) {
        this.vertx = vertx;
        this.service = service;
        this.timeoutSeconds = timeoutSeconds;
        try {
            this.vertx.eventBus().registerDefaultCodec(ServiceException.class,
                    new ServiceExceptionMessageCodec());
        } catch (IllegalStateException ex) {}
        if (timeoutSeconds != -1 && !topLevel) {
            long period = timeoutSeconds * 1000 / 2;
            if (period > 10000)
                period = 10000;
            this.timerId = vertx.setPeriodic(period, this::checkTimedOut);
        } else {
            this.timerId = -1;
        }
        accessed();
    }

    private void checkTimedOut(long id) {
        long now = System.nanoTime();
        if (now - lastAccessed > timeoutSeconds * 1000000000) {
            close();
        }
    }

    @Override
    public void close() {
        if (timerId != -1)
            vertx.cancelTimer(timerId);
        super.close();
    }

    private void accessed() {
        this.lastAccessed = System.nanoTime();
    }

    public void handle(Message<JsonObject> message) {
        try {
            JsonObject json = message.body();
            String action = message.headers().get("action");
            if (action == null)
                throw new IllegalStateException("action not specified");
            accessed();

            switch(action) {
                case "valueFor": {
                    service.valueFor((java.lang.String) json.getValue("sensorId"),
                            HelperUtils.createHandler(message));
                    break;
                }
                case "average": {
                    service.average(HelperUtils.createHandler(message));
                    break;
                }
                default: throw new IllegalStateException("Invalid action: " + action);
            }
        } catch (Throwable t) {
            message.reply(new ServiceException(500, t.getMessage()));
            throw t;
        }
    }
}
