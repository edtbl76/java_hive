package HeatSensor_2;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import io.vertx.ext.web.codec.BodyCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CollectorService extends AbstractVerticle {
    private final Logger logger = LoggerFactory.getLogger(CollectorService.class);
    private WebClient webClient;

    @Override
    public void start() {
        webClient = WebClient.create(vertx);
        vertx.createHttpServer()
                .requestHandler(this::handleRequest)
                .listen(8080);
    }

    private void handleRequest(HttpServerRequest request) {

        // Collection for Json Responses.
        List<JsonObject> responses = new ArrayList<>();

        // counter for tracking responses
        AtomicInteger counter = new AtomicInteger(0);

        for(int i = 0; i < 3; i++) {
            webClient
                    // We start out by sending a get to the HeatSensor
                    .get(3000 + i, "localhost", "/")

                    /*
                        ResponsePredicates are great for web dev. This generates an error if not 2xx statusCode()
                     */
                    .expect(ResponsePredicate.SC_SUCCESS)

                    /*
                        BodyCodec() is a cool interface that assists w/ the encoding of bodies.
                        - in this case we are creating a json write stream.
                     */
                    .as(BodyCodec.jsonObject())
                    .send(httpResponseAsyncResult -> {
                        if (httpResponseAsyncResult.succeeded()) {
                            responses.add(httpResponseAsyncResult.result().body());
                        } else {
                            logger.error("Shit's broke!", httpResponseAsyncResult.cause());
                        }

                        // when all requests/errors are accounted for, we can move to the next operation
                        /*
                            ACCOUNTed for. (COUNTER). Words are cool.
                         */
                        if (counter.incrementAndGet() == 3) {
                            JsonObject data = new JsonObject()
                                    .put("data", new JsonArray(responses));
                            sendToSnapshot(request, data);
                        }
                    });
        }
    }


    private void sendToSnapshot(HttpServerRequest request, JsonObject data) {
        webClient
                // hit the SnapshotService
                .post(4000, "localhost", "/")
                .expect(ResponsePredicate.SC_SUCCESS)
                .sendJsonObject(data, httpResponseAsyncResult -> {
                    if (httpResponseAsyncResult.succeeded()) {
                        sendResponse(request, data);
                    } else {
                        logger.error("Snapshot failure! Everybody run!!!!", httpResponseAsyncResult.cause());
                        request.response().setStatusCode(500).end();
                    }
                });

    }

    private void sendResponse(HttpServerRequest request, JsonObject data) {
        request.response()
                .putHeader("Content-Type", "application/json")
                .end(data.encode());    // compact Json text (smaller over pipe)
    }
}
