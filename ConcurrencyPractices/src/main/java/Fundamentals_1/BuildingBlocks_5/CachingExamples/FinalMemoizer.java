package Fundamentals_1.BuildingBlocks_5.CachingExamples;

import java.util.concurrent.*;

public class FinalMemoizer<A, V> implements Computable<A, V> {
    private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> computable;

    public FinalMemoizer(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A argument) throws InterruptedException {
        while (true) {
            Future<V> future = cache.get(argument);
            if(future == null) {
                Callable<V> callable = () -> computable.compute(argument);
                FutureTask<V> futureTask = new FutureTask<>(callable);
                future = cache.putIfAbsent(argument, futureTask);
                if (future == null) {
                    future = futureTask;
                    futureTask.run();
                }
            }

            try {
                return future.get();
            } catch (CancellationException e) {
                cache.remove(argument, future);
            } catch (ExecutionException e) {
                try {
                    throw e.getCause();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }

        }
    }
}
