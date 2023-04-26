package org.tbl.microprofile.faulttolerance;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@ApplicationScoped
public class CoffeeRepositoryService {

    private Map<Integer, Coffee> coffeeMap = new HashMap<>();
    private AtomicLong counter = new AtomicLong(0);

    public CoffeeRepositoryService() {
        coffeeMap.put(1, new Coffee(1, "Fernandez Espresso", "Columbia", 23));
        coffeeMap.put(2, new Coffee(2, "La Scala Whole Beans", "Bolivia", 18));
        coffeeMap.put(3, new Coffee(3, "Dak Lak Filter", "Vietnam", 25));
    }

    public List<Coffee> listAll() {
        return new ArrayList<>(coffeeMap.values());
    }

    public Coffee findById(Integer id) {
        return coffeeMap.get(id);
    }

    public List<Coffee> recommend(Integer id) {
        if (id == null) {
            return Collections.emptyList();
        }

        return coffeeMap.values()
                .stream()
                .filter(coffee -> !id.equals(coffee.getId()))
                .limit(2)
                .collect(Collectors.toList());

    }

    @CircuitBreaker(requestVolumeThreshold = 4)
    public Integer getAvailability(Coffee coffee) {
        maybeFail();
        return new Random().nextInt(30);
    }

    /*
        Chaos Code
     */
    private void maybeFail() {
        // artifical failure
        final Long invocationNumber = counter.getAndIncrement();

        // alternates 2 successful/2 failure invocations
        if (invocationNumber % 4 > 1) {
            throw new RuntimeException("Service Failed");
        }
    }
}
