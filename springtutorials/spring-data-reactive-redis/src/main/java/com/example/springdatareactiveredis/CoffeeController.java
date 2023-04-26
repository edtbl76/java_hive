package com.example.springdatareactiveredis;


import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

@AllArgsConstructor
public class CoffeeController {

    private final ReactiveRedisOperations<String, Coffee> coffeeOperations;

    @GetMapping("/coffees")
    public Flux<Coffee> all() {
        return coffeeOperations.keys("*")
                .flatMap(coffeeOperations.opsForValue()::get);
    }
}
