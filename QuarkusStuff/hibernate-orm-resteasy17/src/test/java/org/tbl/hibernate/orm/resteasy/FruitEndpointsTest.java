package org.tbl.hibernate.orm.resteasy;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.http.HttpMethod.*;

@QuarkusTest
public class FruitEndpointsTest {

    @Test
    public void testListAllFruits() {

        // LIST
        listFruits(
                containsString("Cherry"),
                containsString("Lime"),
                containsString("Coconut"));

        // UPDATE Cherry (to Banana)
        writeFruits(PUT,
                "{\"name\" : \"Banana\"}",
                containsString("\"id\":"),
                containsString("\"name\":\"Banana\""));

        // CHECK
        listFruits(
                not(containsString("Cherry")),
                containsString("Banana"),
                containsString("Lime"),
                containsString("Coconut"));

        // DELETE Banana
        removeFruit();

        // CHECK
        listFruits(
                not(containsString("Banana")),
                containsString("Lime"),
                containsString("Coconut"));

        // Create Cherry again
       writeFruits(POST,
               "{\"name\" : \"Cherry\"}",
               containsString("\"id\":"),
               containsString("\"name\":\"Cherry\""));

        // CHECK
        listFruits(
                not(containsString("Banana")),
                containsString("Cherry"),
                containsString("Lime"),
                containsString("Coconut"));
    }


    private void listFruits(Matcher<?> matcher, Matcher<?>... matchers) {
        given()
                .when()
                .get("/fruits/")
                .then()
                .statusCode(200)
                .body(matcher, matchers);
    }

    private void writeFruits(HttpMethod httpMethod, String payload, Matcher<?> matcher, Matcher<?>... matchers) {
        RequestSpecification requestSpecification= given()
                .when()
                .body(payload)
                .contentType(APPLICATION_JSON);

        ValidatableResponse response;
        // WRITE
        if (httpMethod == POST) {
            response = requestSpecification
                    .post("/fruits/")
                    .then()
                    .statusCode(201);
        // UPDATE
        } else if (httpMethod == PUT) {
            response = requestSpecification
                    .put("/fruits/1")
                    .then()
                    .statusCode(200);
        } else {
            throw new IllegalArgumentException("Not a valid request type");
        }

        response.body(matcher, matchers);
    }

    private void removeFruit() {
        given()
                .when()
                .delete("/fruits/1")
                .then()
                .statusCode(204);
    }
}
