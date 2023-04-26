package IntegOfJUnit5WithExternalFrameworks_5.example.junit5RESTPublic;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class PublicRestServicesTest {

    public static final String ECHO_JSON = "http://echo.jsontest.com/";
    public static final String IPINFO = "http://ipinfo.io/8.8.8.8";

    @Test
    void testEchoService() {
        String key = "foo";
        String value = "bar";

        given()
                .when().get(ECHO_JSON + key + "/" + value)
                .then().assertThat().statusCode(200).body(key, equalTo(value))
        ;
    }


    @Test
    void testCountryService() {
        given()
                .when().get(IPINFO)
                .then().assertThat().statusCode(200)
                .body("hostname",equalTo("dns.google"));

    }
}
