package com.example.actuatorservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.http.HttpStatus.OK;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.port=0"})
class ActuatorServiceApplicationTests {

    @LocalServerPort
    private int port;

    @Value("${local.management.port}")
    private int management;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturn200WhenSendingRequestToController() {
        var entity = this.testRestTemplate
                .getForEntity("http://localhost:" + this.port + "/hello-world", Map.class);

        then(entity.getStatusCode()).isEqualTo(OK);
    }

    @Test
    public void shouldReturn200WhenSendingRequestToManagementEndpoint() {
        var entity = this.testRestTemplate
                .getForEntity("http://localhost:" + this.management + "/actuator", Map.class);

        then(entity.getStatusCode()).isEqualTo(OK);
    }

}
