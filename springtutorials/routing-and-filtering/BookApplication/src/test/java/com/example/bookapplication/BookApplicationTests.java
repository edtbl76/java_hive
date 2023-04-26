package com.example.bookapplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testAvailable() {
        String response = testRestTemplate.getForObject("/available", String.class);
        assertThat(response).isEqualTo("Spring in Action");
    }

    @Test
    public void testCheckedOut() {
        String response = testRestTemplate.getForObject("/checked-out", String.class);
        assertThat(response).isEqualTo("Spring Boot in Action");
    }

}
