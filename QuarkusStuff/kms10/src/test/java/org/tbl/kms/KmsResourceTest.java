package org.tbl.kms;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.*;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;


@QuarkusTest
@QuarkusTestResource(KmsResource.class)
public class KmsResourceTest {

    private final static String TEST_TEXT = "Quarkus rules!";

    @ParameterizedTest
    @ValueSource(strings = {"sync", "async"})
    void testResource(final String testedResource) {
        // Encrypt
        String encryptedText = given()
                .pathParam("resource", testedResource)
                .header(CONTENT_TYPE, TEXT_PLAIN)
                .body(TEST_TEXT)
                .when()
                .post("/{resource}/encrypt")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        assertThat(encryptedText, notNullValue());

        // Decrypt
        given()
                .pathParam("resource", testedResource)
                .headers(CONTENT_TYPE, TEXT_PLAIN)
                .body(encryptedText)
                .when()
                .post("/{resource}/decrypt")
                .then()
                .statusCode(OK.getStatusCode())
                .body(containsString(TEST_TEXT));
    }
}
