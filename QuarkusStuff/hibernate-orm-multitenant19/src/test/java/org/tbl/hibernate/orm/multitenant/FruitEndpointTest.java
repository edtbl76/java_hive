package org.tbl.hibernate.orm.multitenant;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

@QuarkusTest
public class FruitEndpointTest {

    @Test
    public void testLIstAllFruitsBaseSchema() {

        // Ensure initial state
        Fruit banana = findOrCreate("", "Banana");
        deleteIfExist("", "Clementine");

        // List All
        given()
                .when().get("/fruits")
                .then()
                .statusCode(200)
                .body(
                        containsString("Lime"),
                        containsString("Coconut"),
                        containsString("Banana")
                );


        // Delete Banana
        given()
                .when().delete("/fruits/" + banana.getId())
                .then()
                .statusCode(204);

        // Confirm the banana isn't there.
        given()
                .when().get("/fruits")
                .then()
                .statusCode(200)
                .body(
                        containsString("Lime"),
                        containsString("Coconut"),
                        not(containsString("Banana"))
                );


        // Create Clementine
        given()
                .when()
                .body("{\"name\":\"Clementine\"}")
                .contentType(APPLICATION_JSON)
                .post("/fruits")
                .then()
                .statusCode(201);

        // Confirm state
        given()
                .when().get("/fruits")
                .then()
                .statusCode(200)
                .body(
                        containsString("Clementine"),
                        containsString("Lime"),
                        containsString("Coconut"),
                        not(containsString("Banana"))
                );
    }


    @Test
    public void testLIstAllFruitsMyCompanySchema() {

        // Ensure initial state
        Fruit apricot = findOrCreate("/mycompany", "Apricot");
        deleteIfExist("/mycompany", "Nectarine");

        // List All
        given()
                .when().get("/mycompany/fruits")
                .then()
                .statusCode(200)
                .body(
                        containsString("Peach"),
                        containsString("Apricot"),
                        containsString("Plum")
                );


        // Delete Apricot
        given()
                .when().delete("/mycompany/fruits/" + apricot.getId())
                .then()
                .statusCode(204);

        // Confirm the banana isn't there.
        given()
                .when().get("/mycompany/fruits")
                .then()
                .statusCode(200)
                .body(
                        containsString("Peach"),
                        not(containsString("Apricot")),
                        containsString("Plum")
                );


        // Create Nectarine
        given()
                .when()
                .body("{\"name\":\"Nectarine\"}")
                .contentType(APPLICATION_JSON)
                .post("/mycompany/fruits")
                .then()
                .statusCode(201);

        // Confirm state
        given()
                .when().get("/mycompany/fruits")
                .then()
                .statusCode(200)
                .body(
                        containsString("Nectarine"),
                        containsString("Peach"),
                        not(containsString("Apricot")),
                        containsString("Plum")
                );
    }
    /*
        Helpers
     */
    private void create(String tenantPrefix, String fruitName) {
        given()
                .when()
                .body("{\"name\":\"" + fruitName + "\"}")
                .contentType(APPLICATION_JSON)
                .post(tenantPrefix + "/fruits")
                .then()
                .statusCode(201);
    }

    private void deleteIfExist(String tenantPrefix, String fruitName) {
        Response response = given()
                .param("type", "name")
                .param("value", fruitName)
                .when()
                .get(tenantPrefix + "/fruitsByName");

        if (response.statusCode() == 200) {
            Fruit fruit = response.as(Fruit.class);
            given()
                    .when()
                    .delete(tenantPrefix + "/fruits/" + fruit.getId())
                    .then()
                    .statusCode(204);
        }
    }

    private Fruit find(String tenantPrefix, String fruitName) {
        Response response = given()
                .param("type", "name")
                .param("value", fruitName)
                .when()
                .get(tenantPrefix + "/fruitsFindBy");

        if (response.statusCode() == 404) {
            return null;
        }

        if (response.statusCode() == 200) {
            return response.as(Fruit.class);
        }

        // Else error
        throw new IllegalStateException("Unknown status finding '" + fruitName + ": " + response);

    }

    private Fruit findOrCreate(String tenantPrefix, String name) {
        Fruit fruit = find(tenantPrefix, name);
        if (fruit == null) {
            create(tenantPrefix, name);
            return find(tenantPrefix, name);
        }
        return fruit;
    }
}
