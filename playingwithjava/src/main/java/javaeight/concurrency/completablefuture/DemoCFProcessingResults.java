package javaeight.concurrency.completablefuture;

import utils.Generated;

import java.util.concurrent.*;

import static java.lang.Thread.*;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.concurrent.Executors.newFixedThreadPool;

@Generated
@SuppressWarnings({"java:S106", "java:S2142", "java:S125"})
public class DemoCFProcessingResults {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 1. thenApply()
        System.out.println("thenApply(): " + thenApplyDemo());

        // 2. thenApplyAsync();
        System.out.println("thenApplyAsync(): " + thenApplyAsyncDemo());

        // 3. thenApplyAsync() w/ custom executor
        System.out.println("thenApplyAsync(): [Custom Executor]" + thenApplyAsyncCustomPoolDemo());

        // 4. thenAccept()
        thenAcceptDemo();

        // 5. thenRun()
        thenRunDemo();

    }

    /*
        thenApply()
        - PARAM: Function<T, R> (Input T, Output R)
        - Function processes the result of thenApply and returns CompletableFuture<R>
        - executes code in the SAME THREAD as the initial supplyAsync().
            - if supplyAsync() executes sufficiently fast, thenApply() will execute in the main thread. <- BAD!
     */
    private static Integer thenApplyDemo() throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future = supplyAsync(
                () -> doWork(1));

        CompletableFuture<Integer> resultFuture = future.thenApply(integer -> {
            System.out.println(currentThread().getName());
            return integer * 2;
        });

        return resultFuture.get();
    }


    /*
        thenApplyAsync()
        - same as thenApply(), but it executes in a different thread.
     */
    private static Integer thenApplyAsyncDemo() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = supplyAsync(
                () -> doWork(1));

        CompletableFuture<Integer> resultFuture = future.thenApplyAsync(integer -> {
            System.out.println(currentThread().getName());
            return integer * 2;
        });

        return resultFuture.get();
    }


    /*
        thenApplyAsync() w/ custom executor.
        (Default is ForkJoinPool)
     */
    private static Integer thenApplyAsyncCustomPoolDemo() throws ExecutionException, InterruptedException {
        ExecutorService executorService = newFixedThreadPool(5);

        CompletableFuture<Integer> future = supplyAsync(() -> doWork(1));

        CompletableFuture<Integer> resultFuture = future.thenApplyAsync(integer -> {
            System.out.println(currentThread().getName());
            return integer * 2;
        }, executorService);

        return resultFuture.get();
    }


    /*
        thenAccept()
            PARAM: Consumer<T>
            RETURNS; CompletableFuture<Void> <- i.e. we don't want to return anything.
     */
    private static void thenAcceptDemo() {

        CompletableFuture<Integer> future = supplyAsync(() -> doWork(1));

        future.thenAccept(integer -> {
            System.out.println(currentThread().getName());
            System.out.println("The value is: " + integer);
        });
    }

    /*
        thenRun() similar to thenAccept (no return value)
        - PARAM - Runnable
        - Returns CompletableFuture.

          DIFFERENCE:
            - thenAccept() has access to the result of the CompletedFuture it is attached to.
            (This is how we could print it!)
            - thenRun() doesn't have access to the Future's result.
                - it is SUPER Async.
     */
    private static void thenRunDemo() {
        CompletableFuture<Integer> future = supplyAsync(() -> doWork(1));

        future.thenRun(() -> {
            System.out.println(currentThread().getName());
            System.out.println("Hello, World! I have absolutely no access to the value!");
        });
    }

    @SuppressWarnings("SameParameterValue")
    private static int doWork(int length) {
        try {
            TimeUnit.SECONDS.sleep(length);
            System.out.println(currentThread().getName());
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
        return 50;
    }
}
