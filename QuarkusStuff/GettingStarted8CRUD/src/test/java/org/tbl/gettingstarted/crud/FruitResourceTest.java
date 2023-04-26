package org.tbl.gettingstarted.crud;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;

@QuarkusTest
@QuarkusTestResource(DatabaseResource.class)
public class FruitResourceTest {

    @Test
    public void testListAllFruits() {
        given()
                .when().get("/fruits")
                .then()
                .statusCode(200)
                .body(
                        containsString("Raspberry"),
                        containsString("Strawberry"),
                        containsString("Lime"),
                        containsString("Lemon"));
    }

    @Test
    public void testGetOne() {
        given()
                .when().get("/fruits/4")
                .then()
                .statusCode(200)
                .body(containsString("Lemon"));
    }

    @Test
    public void testCreateOne() {
        Fruit watermelon = new Fruit(5, "Watermelon");
        given()
                .when().post("/fruit")
                .then()
                .statusCode(405);

        given()
                .when().get("/fruits/5")
                .then()
                .statusCode(200)
                .body(containsString("Watermelon"));
    }

    @Test
    public void testUpdate() {
        given()
                .when().put("/fruits/5", "Cherry")
                .then()
                .statusCode(200)
                .body(containsString("Cherry"));
    }

    @Test
    public void testDelete() {
        given()
                .when().delete("/fruits/5")
                .then()
                .statusCode(204);

        given()
                .when().get("/fruits")
                .then()
                .statusCode(200)
                .body(
                        not(containsString("Cherry")),
                        not(containsString("Watermelon")),
                        containsString("Raspberry"),
                        containsString("Strawberry"),
                        containsString("Lime"),
                        containsString("Lemon")
                );
    }

}
