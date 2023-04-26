package com.example.restservicecors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpHeaders.ORIGIN;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestServiceCorsApplicationTests {

	private static final String HOST = "http://localhost:8080";

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void corsWithAnnotation() {
		ResponseEntity<Greeting> responseEntity = this.testRestTemplate.exchange(
				RequestEntity
						.get(uri("/greeting"))
						.header(ORIGIN, HOST)
						.build(),
				Greeting.class);

		assertEquals(OK, responseEntity.getStatusCode());
		assertEquals(HOST, responseEntity.getHeaders().getAccessControlAllowOrigin());
		assertEquals("Hello, World!", Objects.requireNonNull(responseEntity.getBody()).getContent());
	}

	@Test
	public void corsWithJavaConfig() {
		ResponseEntity<Greeting> responseEntity = this.testRestTemplate.exchange(
				RequestEntity
						.get(uri("/greeting-javaconfig"))
						.header(ORIGIN, HOST)
						.build(),
				Greeting.class);
		assertEquals(OK, responseEntity.getStatusCode());
		assertEquals(HOST, responseEntity.getHeaders().getAccessControlAllowOrigin());
		assertEquals("Hello, World!", Objects.requireNonNull(responseEntity.getBody()).getContent());
	}

	private URI uri(String path) {
		return testRestTemplate.getRestTemplate().getUriTemplateHandler().expand(path);
	}

}
