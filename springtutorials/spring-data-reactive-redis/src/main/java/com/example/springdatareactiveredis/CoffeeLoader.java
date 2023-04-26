package com.example.springdatareactiveredis;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.UUID;

@AllArgsConstructor
@Component
public class CoffeeLoader {

    private final ReactiveRedisConnectionFactory factory;
    private final ReactiveRedisOperations<String, Coffee> coffeeOperations;

    @PostConstruct
    public void loadData() {
        factory.getReactiveConnection()
                .serverCommands()
                .flushAll()
                .thenMany(
                        Flux.just("Jet Black Redis", "Darth Redis", "Black Alert Redis")
                                .map(name -> new Coffee(UUID.randomUUID().toString(), name))
                                .flatMap(coffee -> coffeeOperations.opsForValue().set(coffee.getId(), coffee)))
                .thenMany(coffeeOperations.keys("*").flatMap(coffeeOperations.opsForValue()::get))
                .subscribe(System.out::println);
    }

}
