package org.tbl.kogito;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.Is.is;

@QuarkusTest
public class PersonProcessTest {

    public static final String QUARTERBACK = "{\"person\": {\"name\":\"Russell Wilson\", \"age\": 32}}";
    public static final String TALLADEGA ="{\"person\": {\"name\":\"Baby Jesus\", \"age\": 1}}";


    @Test
    public void testAdult() {
        shortcut(given().body(QUARTERBACK))
                .body("person.adult", is(true));
    }

    @Test
    public void testNotAdult() {
        shortcut(given().body(TALLADEGA))
                .body("person.adult", is(false));
    }

    /*
        Demonstrable Code Reuse.
     */
    private ValidatableResponse shortcut(RequestSpecification requestSpecification) {
        return requestSpecification
                .contentType(JSON)
                .when()
                .post("/persons")
                .then()
                .statusCode(201);
    }
}
