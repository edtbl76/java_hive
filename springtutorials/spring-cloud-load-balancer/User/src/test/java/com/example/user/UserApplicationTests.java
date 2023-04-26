package com.example.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UserApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserApplicationTests {

    private ConfigurableApplicationContext context1;
    private ConfigurableApplicationContext context2;
    private ConfigurableApplicationContext context3;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;


    @BeforeEach
    void startApps() {
        context1 = startApp(8090);
        context2 = startApp(9092);
        context3 = startApp(9999);
    }


    @AfterEach
    void closeApps() {
        context1.close();
        context2.close();
        context3.close();
    }

    @Test
    void shouldRoundRobinOverInstanceWhenCallingServicesViaLoadBalancer() {

        String entityString = "http://localhost:" + port;

        ResponseEntity<String> response1 = testRestTemplate
                .getForEntity(entityString + "/hi", String.class);

        ResponseEntity<String> response2 = testRestTemplate
                .getForEntity(entityString + "/hi", String.class);

        ResponseEntity<String> response3 = testRestTemplate
                .getForEntity(entityString + "/hello", String.class);

        then(response1.getStatusCode()).isEqualTo(OK);
        then(response1.getBody()).isEqualTo("1, Homer!");

        then(response2.getStatusCode()).isEqualTo(OK);
        then(response2.getBody()).isEqualTo("2, Homer!");

        then(response3.getStatusCode()).isEqualTo(OK);
        then(response3.getBody()).isEqualTo("3, Marge!");

    }

    private ConfigurableApplicationContext startApp(int port) {
        return SpringApplication.run(TestApplication.class,
                "--server.port=" + port,
                "--spring.jmx.enabled=false");
    }

}
