package org.tbl.s3;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.Response.Status.CREATED;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@QuarkusTestResource(S3Resource.class)
public class S3ResourceTest {

    private static final String FILE_NAME_PREFIX = "test-file-";
    private static final String FILE_MIMETYPE = "text/plain";

    @ParameterizedTest
    @ValueSource(strings = {"s3", "async-s3"})
    void testResource(final String testedResource) {
        List<String> data = Arrays.asList("Ibanez", "Jackson");

        // Upload
        data.forEach(guitar ->
                given()
                    .pathParam("resource", testedResource)
                    .multiPart("file", guitar)
                    .multiPart("filename", FILE_NAME_PREFIX + guitar)
                    .multiPart("mimetype", FILE_MIMETYPE)
                    .when()
                    .post("/{resource}/upload")
                    .then()
                    .statusCode(CREATED.getStatusCode()));


        // List them
        given()
                .pathParam("resource", testedResource)
                .when().get("/{resource}")
                .then()
                .statusCode(200)
                // Objects are sorted by modified data, so the last added will be the first from list files
                .body("size()", equalTo(2))
                .body("[0].objectKey", equalTo(FILE_NAME_PREFIX + data.get(1)))
                .body("[0].size", equalTo(data.get(1).length()))
                .body("[1].objectKey", equalTo(FILE_NAME_PREFIX + data.get(0)))
                .body("[1].size", equalTo(data.get(0).length()));

        // download
        data.forEach(guitar ->
                given()
                        .pathParam("resource", testedResource)
                        .pathParam("objectKey", FILE_NAME_PREFIX + guitar)
                        .when().get("/{resource}/download/{objectKey}")
                        .then()
                        .statusCode(200)
                        .body(equalTo(guitar)));
    }
}
