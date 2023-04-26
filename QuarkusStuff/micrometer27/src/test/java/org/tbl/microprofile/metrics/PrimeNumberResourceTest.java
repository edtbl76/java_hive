package org.tbl.microprofile.metrics;

import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@QuarkusTest
public class PrimeNumberResourceTest {

    @Test
    void checkCounter() {
        Arrays.asList("31", "33", "887", "900").forEach(s -> get("/" + s));

        /*
            Prometheus results have all of the data.
            We're looking for
            - 4 successful requests (200) to /{number}
            - 3 attempts at non-trivial detection of a prime number
            - max prime seen
         */
        given()
                .get("/metrics")
                .then()
                .statusCode(200)
                .log().all()
                .body(containsString("prime_number_max 887.0"))
                .body(containsString("prime_number_test_seconds_count 3.0"))
                .body(CoreMatchers.containsString(
                        "http_server_requests_seconds_count{method=\"GET\"," +
                                "outcome=\"SUCCESS\",status=\"200\",uri=\"/{number}\",} 4.0"));

    }
}
