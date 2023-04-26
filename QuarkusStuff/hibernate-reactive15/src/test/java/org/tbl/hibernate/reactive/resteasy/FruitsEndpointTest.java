package org.tbl.hibernate.reactive.resteasy;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class FruitsEndpointTest {

    @Test
    public void testListAllFruits() {
        // Confirms initial state of seeded db
        Response response = given()
                .when()
                .get("/fruits")
                .then()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .extract().response();
        assertThat(response.jsonPath().getList("name"))
                .containsExactlyInAnyOrder("Cherry", "Lime", "Coconut");


       // Update Cherry to Mango
       given()
               .when()
               .body("{\"name\" : \"Mango\"}").contentType(APPLICATION_JSON)
               .put("/fruits/1")
               .then()
               .statusCode(200)
               .body(containsString("\"id\":"), containsString("\"name\":\"Mango\""));

       // Re-List, confirm new data
        response = given()
                .when()
                .get("/fruits")
                .then()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .extract().response();
        assertThat(response.jsonPath().getList("name"))
                .containsExactlyInAnyOrder("Mango", "Lime", "Coconut");

        // Delete Mango
        given()
                .when().delete("/fruits/1")
                .then().statusCode(204);


        // Re-List, confirm new data
        given()
                .when()
                .get("/fruits")
                .then()
                .statusCode(200)
                .body(not(containsString("Mango")),
                        containsString("Lime"),
                        containsString("Coconut"));

        // Create The Cherry
        given()
                .when()
                .body("{\"name\" : \"Cherry\"}").contentType(APPLICATION_JSON)
                .post("/fruits")
                .then()
                .statusCode(201)
                .body(containsString("\"id\":"), containsString("\"name\":\"Cherry\""));


        response = given()
                .when()
                .get("/fruits")
                .then()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .extract().response();
        assertThat(response.jsonPath().getList("name"))
                .containsExactlyInAnyOrder("Cherry", "Lime", "Coconut");
    }

    @Test
    public void testEntityNotFoundForDelete() {
       given()
               .when().delete("/fruits/867")
               .then()
               .statusCode(404)
               .body(emptyString());
    }

    @Test
    public void testEntityNotFoundForUpdate() {
        given()
                .when().body("{\"name\" : \"Watermelon\"}").contentType(APPLICATION_JSON)
                .put("/fruits/5309")
                .then().statusCode(404)
                .body(emptyString());
    }

}
