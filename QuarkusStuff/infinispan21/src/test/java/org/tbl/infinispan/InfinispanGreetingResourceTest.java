package org.tbl.infinispan;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
@QuarkusTestResource(CacheResource.class)
public class InfinispanGreetingResourceTest {

    @Test
    void testHelloEndpoint() {
        given()
                .when()
                .get("/infinispan")
                .then()
                .statusCode(200)
                .body(is("Hello World, Infinispan is up!"));
    }

}
