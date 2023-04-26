package com.example.reactiverestservice;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class GreetingClient {

    private final WebClient client;

    /*
        NOTE: Spring Boot auto-configures a WebClient.Builder instance w/ defaults and customizations
        - Worth using it for most use cases.
     */
    public GreetingClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("http://localhost:8080").build();
    }

    public Mono<String> getMessage() {
        return this.client
                .get()
                .uri("/hello")
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Greeting.class)
                .map(Greeting::getMessage);
    }
}
