package com.example.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookstoreApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void recommendedTest() {
        webTestClient.get()
                .uri("/recommended")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).value(s ->
                        s.equals("Spring in Action (Manning), " +
                                "Cloud Native Java (O'Reilly), Learning Spring Boot (Packt)"));
    }

}
