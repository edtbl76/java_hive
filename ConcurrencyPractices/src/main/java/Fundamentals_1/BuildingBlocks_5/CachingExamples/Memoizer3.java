package Fundamentals_1.BuildingBlocks_5.CachingExamples;

import java.util.Map;
import java.util.concurrent.*;

public class Memoizer3<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> computable;

    public Memoizer3(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A argument) throws InterruptedException {
        Future<V> future = cache.get(argument);
        if (future == null) {
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return computable.compute(argument);
                }
            };
            FutureTask<V> futureTask = new FutureTask<>(eval);
            future = futureTask;
            cache.put(argument, futureTask);
            futureTask.run();
        }
        try {
            return future.get();
        } catch (ExecutionException e) {
            throw launderThrowable(e.getCause());
        }
    }

    public static RuntimeException launderThrowable(Throwable cause) {
        if (cause instanceof RuntimeException) {
            return (RuntimeException) cause;
        } else {
            throw new IllegalStateException("Not unchecked", cause);
        }
    }
}
