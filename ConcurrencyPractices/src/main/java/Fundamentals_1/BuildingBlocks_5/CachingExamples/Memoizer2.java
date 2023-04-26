package Fundamentals_1.BuildingBlocks_5.CachingExamples;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Memoizer2<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> computable;

    public Memoizer2(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A argument) throws InterruptedException {
        Optional<V> result = Optional.ofNullable(cache.get(argument));
        if (result.isEmpty()) {
            result = Optional.of(computable.compute(argument));
            cache.put(argument, result.get());
        }
        return result.get();
    }
}
