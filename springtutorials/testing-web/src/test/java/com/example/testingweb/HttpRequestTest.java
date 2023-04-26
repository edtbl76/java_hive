package com.example.testingweb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

/*
    Asserts behavior of application
    - start app
    - listen for connection
    - send HTTP Request
    - assert the response

 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() {
        String url = "http://localhost:" + this.port + "/";
        assertThat(this.testRestTemplate.getForObject(url, String.class))
                .contains("Hello, World!");
    }
}
