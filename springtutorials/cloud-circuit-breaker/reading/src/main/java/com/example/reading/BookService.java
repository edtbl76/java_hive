package com.example.reading;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class BookService {

    private final WebClient webClient;
    private final ReactiveCircuitBreaker circuitBreaker;

    @SuppressWarnings("rawtypes")
    public BookService(ReactiveCircuitBreakerFactory factory) {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8090")
                .build();

        this.circuitBreaker = factory.create("recommended");
    }

    public Mono<String> readingList() {
        return circuitBreaker
                .run(webClient.get()
                        .uri("/recommended")
                        .retrieve()
                        .bodyToMono(String.class), throwable -> {
                    log.warn("Error making request to book service", throwable);
                    return Mono.just("Cloud Native Java (O'Reilly)");
                });
    }
}
