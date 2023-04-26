package org.tlb.dynamodb;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.*;
import static javax.ws.rs.core.MediaType.*;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@QuarkusTestResource(DynamoResource.class)
public class DynamoResourceTest {

    private static final BiFunction<String, String, String> FRUIT =
            (name, description) -> String.format("{\"name\":\"%s\", \"description\":\"%s\"}", name, description);

    @ParameterizedTest
    @ValueSource(strings = {"fruits", "async-fruits"})
    void testResource(final String testedResource) {
        List<String> data = Arrays.asList("Cherry", "Lime");

        // add 'em
        data.forEach(fruit -> {
            given()
                    .pathParam("resource", testedResource)
                    .header(CONTENT_TYPE, APPLICATION_JSON)
                    .body(FRUIT.apply(fruit, "description-" + fruit))
                    .when()
                    .post("/{resource}")
                    .then()
                    .statusCode(OK.getStatusCode());
        });

        // List Fruits
        given()
                .pathParam("resource", testedResource)
                .when().get("/{resource}")
                .then()
                .statusCode(200)
                .body("size()", equalTo(2))
                .body("[0].name", equalTo(data.get(1)))
                .body("[0].description", equalTo("description-" + data.get(1)))
                .body("[1].name", equalTo(data.get(0)))
                .body("[1].description", equalTo("description-" + data.get(0)));


        // Get Single Fruit
        data.forEach(fruit ->
                given()
                        .pathParam("resource", testedResource)
                        .pathParam("key", fruit)
                        .when().get("/{resource}/{key}")
                        .then()
                        .statusCode(200)
                        .body("name", equalTo(fruit))
                        .body("description", equalTo("description-" + fruit))
        );

    }
}
