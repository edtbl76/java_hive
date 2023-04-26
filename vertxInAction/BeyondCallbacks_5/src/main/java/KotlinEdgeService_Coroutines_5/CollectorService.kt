package KotlinEdgeService_Coroutines_5

import io.vertx.core.http.HttpServerRequest
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.client.WebClient
import io.vertx.ext.web.client.predicate.ResponsePredicate
import io.vertx.ext.web.codec.BodyCodec
import io.vertx.kotlin.core.http.listenAwait
import io.vertx.kotlin.core.json.Json
import io.vertx.kotlin.core.json.array
import io.vertx.kotlin.core.json.json
import io.vertx.kotlin.core.json.obj
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.ext.web.client.sendAwait
import io.vertx.kotlin.ext.web.client.sendJsonAwait
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory

class CollectorService : CoroutineVerticle() {
    private val logger = LoggerFactory.getLogger(CollectorService::class.java)

    // lateinit means that field isn't initialized in a constructor
    private lateinit var webClient: WebClient

    override suspend fun start() {
        webClient = WebClient.create(vertx)
        vertx.createHttpServer()
                .requestHandler(this::handleRequest)
                // waits for HTTP server to be started.. throws an exception otherwise.
                .listenAwait(8080)
    }

    private suspend fun fetchTemperature(port: Int) : JsonObject {
        return webClient
                .get(port, "localhost", "/")
                .expect(ResponsePredicate.SC_SUCCESS)
                // as is a special Kotlin keyword, so it has to be escaped when used as a method name.
                .`as`(BodyCodec.jsonObject())
                .sendAwait()
                .body()
    }

    private suspend fun sendToSnapshot(json: JsonObject) {
        webClient
                .post(4000, "localhost", "/")
                .expect(ResponsePredicate.SC_SUCCESS)
                .sendJsonAwait(json)
    }

    private fun handleRequest(request: HttpServerRequest) {
        launch {
            try {
                val t1 = async { fetchTemperature(3000) }
                val t2 = async { fetchTemperature(3001) }
                val t3 = async { fetchTemperature(3002) }

                // This allows us to wait for all values
                val array = Json.array(t1.await(), t2.await(), t3.await())

                // Vert.x has a small Kotlin DSL to make JSON object creation easier.
                val json = json { obj("data" to array) }

                sendToSnapshot(json)
                request.response()
                        .putHeader("Content-Type", "application/json")
                        .end(json.encode())
            } catch (err : Throwable) {
                logger.error("Something went horribly wrong", err)
                request.response().setStatusCode(500).end()
            }
        }
    }
}