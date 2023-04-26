package org.tbl.sns;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.*;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.Matchers.any;

@QuarkusTest
@QuarkusTestResource(SnsResource.class)
public class SnsResourceTest {


    @ParameterizedTest
    @ValueSource(strings = {"sync", "async"})
    void testPublisher(final String testedResource) {
        String shoot = String.format("{\"flavor\":\"%s\", \"spin\":\"%s\"}", "Charm", "1/2");
        given()
                .pathParam("resource", testedResource)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .body(shoot)
                .when()
                .post("/{resource}/cannon/shoot")
                .then()
                .statusCode(200)
                .body(any(String.class));
    }

}
