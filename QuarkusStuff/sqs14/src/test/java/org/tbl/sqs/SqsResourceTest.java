package org.tbl.sqs;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.containsString;

@QuarkusTest
@QuarkusTestResource(SqsResource.class)
public class SqsResourceTest {

    private final BiFunction<String, String, String> QUARK =
            (flavor, spin) -> String.format("{\"flavor\":\"%s\", \"spin\":\"%s\"}", flavor, spin);

    @ParameterizedTest
    @ValueSource(strings = {"sync", "async"})
    void testResource(final String testedResource) {
        List<String> quarks = Arrays.asList("Charm", "Strange", "Bottom", "Upper");

        // FIRE!
        quarks.forEach(quark -> {
            given()
                    .pathParam("resource", testedResource)
                    .header(CONTENT_TYPE, APPLICATION_JSON)
                    .body(QUARK.apply(quark, "1/2"))
                    .when()
                    .post("/{resource}/cannon/shoot")
                    .then()
                    .statusCode(OK.getStatusCode())
                    .body(any(String.class));
        });

        // READ FROM Q
        given()
                .pathParam("resource", testedResource)
                .when()
                .get("/{resource}/shield")
                .then()
                .statusCode(OK.getStatusCode())
                .body(
                        containsString("Charm"),
                        containsString("Strange"),
                        containsString("Bottom")
                );
    }
}
