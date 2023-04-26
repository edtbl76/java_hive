package com.example.resthateoas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.hateoas.MediaTypes.HAL_JSON;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingTraversonIntegrationTests {

    @LocalServerPort
    private int port;


    @Test
    public void envEndpointNotHidden() throws Exception {

        String uri = "http://localhost:" + this.port + "/greeting";
        Traverson traverson = new Traverson(new URI(uri), HAL_JSON);

        String greeting = traverson.follow("self").toObject("$.content");
        assertThat(greeting).isEqualTo("Hello, World!");
    }
}
