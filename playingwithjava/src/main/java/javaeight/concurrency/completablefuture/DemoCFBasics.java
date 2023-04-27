package javaeight.concurrency.completablefuture;


import utils.Generated;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

@Generated
@SuppressWarnings({"java:S106", "ThrowablePrintedToSystemOut", "java:S2142", "all"})
public class DemoCFBasics {


    public static void main(String[] args) {

        /*
            completedFuture()

         */
        System.out.println(completedFutureExample());

        /*
            2. runAsync()
         */
        // uses default ForkJoinPool
        runAsyncDemo();
        // uses custom pool. (iI used newFixedThreadPool)
        runAsyncCustomPool();


        /*
            supplyAsync()
            - returns a value (uses a Supplier instead of a runnable)
         */
        // default/custom same as runAsync()
        supplyAsyncDemo();
        supplyAsyncDemoCustomPool();
    }

    /*
        CompletableFuture interface
        - used for async comp.
        - executed as non-blocking call in a separate thread
        - result is made available when its ready
            - prevents the maim thread from blocking/waiting for completion of the task so that it
            can execute other tasks in parallel.

        - implements CompletionStage and Future
            - CompletionStage is a promise (which promises that the computation will eventually be completed)

            - (this replaces Future, which was the old method used for async comp.)
            - Future was limited, such that it didn't have any methods to combine computations, handle errors
            etc.

                Limits fo Future
                - can't perform additional operations on a Future's result w/o blocking.
                    - get() blocks until computation is complete.

                - doesn't support chaining.
                    - executing one Future, and triggering another once the first is complete isn't possible

                - multiple Futures can't be combined together.

                - no exception handling.
     */

    @SuppressWarnings("unused")
    public static CompletableFuture<Integer> getSquareAsynchronously(int number) {
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            await().atMost(500, MILLISECONDS);

            // NOTE: calling complete() is how the CF is completed, and releases the calling thread.
            completableFuture.complete(number * number);
            return null;
        });

        return completableFuture;
    }

    public static String completedFutureExample() {

        /*
            if we are sure about the result of a computation, we can use the static completedFuture
            method w/ an argument representing the result of the computaiton.

            get() will never block.

         */
        CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("Hello!");

        try {
            return completableFuture.get();
        } catch (ExecutionException | InterruptedException e) {
            return String.valueOf(e);
        }
    }

    private static void sleepForThreeSeconds() {
        try {
            SECONDS.sleep(3);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private static String sleepForThreeSecondsAndReturn(String string) {
        try {
            SECONDS.sleep(3);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return string;
    }

    public static void runAsyncDemo() {
        /*
            runAsync()
                - static method that runs some bg tasks async
                - returns CompletableFuture<Void>
                - parameter: Runnable

            Useful if we want to execute code in parallel, but we don't need a result.

            Uses ForkJoinPool by default.

         */
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            sleepForThreeSeconds();
            System.out.println("Doing some work: " + currentThread().getName());


        });

        System.out.println("Prints immediately: " + currentThread().getName());

        try {
            future.get();
        } catch (ExecutionException | InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("Prints after future completes: " + currentThread().getName());

    }



    private static void runAsyncCustomPool() {

        Executor executor = Executors.newFixedThreadPool(5);

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            sleepForThreeSeconds();
            System.out.println("Doing some processsing " + currentThread().getName());
        }, executor);

        System.out.println("Prints immediately " + currentThread().getName());

        try {
            future.get();
        } catch (ExecutionException | InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("Prints after future completes " + currentThread().getName());
    }


    private static void supplyAsyncDemo() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(
                () -> sleepForThreeSecondsAndReturn("Hello, World!"));

        System.out.println("Prints immediately: " + currentThread().getName());

        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e);
        }

        System.out.println("Prints when the future completes: " + currentThread().getName());
    }

    private static void supplyAsyncDemoCustomPool() {
        Executor executor = Executors.newFixedThreadPool(5);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(
                () -> sleepForThreeSecondsAndReturn("Hello, World!"), executor);

        System.out.println("Prints immediately: " + currentThread().getName());

        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e);
        }

        System.out.println("Prints when the future completes: " + currentThread().getName());
    }
}
