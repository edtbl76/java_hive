package javaeight.concurrency.completablefuture;

import utils.Generated;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.CompletableFuture.*;
import static java.util.concurrent.TimeUnit.SECONDS;

@Generated
@SuppressWarnings({"java:S106", "java:S2142", "ThrowablePrintedToSystemOut"})
public class DemoCFChainingAndCombining {

    static Random random = new Random();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //1. thenCompose()
        System.out.println("thenCompose(): " + thenComposeDemo());


        //2. thenCombine()
        System.out.println("thenCombine(): " + thenCombineDemo());

        //3 allOf()
        allOfDemo();

        //4 join()
        System.out.println("join(): " + joinDemo());

        //5. anyOg()
        System.out.println("anyOf(): " + anyOfDemo());


    }

    /*
        thenCompose()
        - PARAM: Function<? super T, ? extends CompletionStage<U>>
            - (this is the function that has the result of the upstream computation.
        - RETURNS: CompletableFuture

            thenCompose() vs. thenApply()?
            thenCompose() provides a flattened result.
     */
    private static Integer thenComposeDemo() throws ExecutionException, InterruptedException {

        // Create future that returns an Integer
        CompletableFuture<Integer> future = supplyAsync(() -> doWork(1));

        CompletableFuture<Integer> resultFuture = future.thenCompose(integer -> supplyAsync(() -> integer * 2));

        return resultFuture.get();

    }


    /*
        thenCombine()
            - PARAMS: Future, BiFunction <-- callback.
            -

            similar to thenCompose(), but it will do something w/ the results.
     */
    private static Integer thenCombineDemo() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = supplyAsync(() -> doWork(1));

        CompletableFuture<Integer> resultFuture = future.thenCombine(
                CompletableFuture.supplyAsync(() -> random.nextInt(20)), Integer::sum);

        return resultFuture.get();
    }


    /*
        allOf()
        - runs multiple futures in parallel and combines their results.
        - returns a NEW CompletableFuture when all of the provided CFs are completed.
        - if any of the CFs complete w/ an exception, the returned CF also copmletes, w/ a CompletionException
            holding the exception as its cause.
        - the results of ANY given CF aren't reflected in the returned CF, but can be obtained
        = if no CFs are provided, a completed CF is returned w/ null
     */
    private static void allOfDemo() throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future1 = supplyAsync(() -> 50);
        CompletableFuture<Integer> future2 = supplyAsync(() -> 40);
        CompletableFuture<Integer> future3 = supplyAsync(() -> 30);

        CompletableFuture<Void> finalFuture = allOf(future1, future2, future3);

        finalFuture.get();
        System.out.println("Code that executes after all futures complete!");
    }

    /*
        join()
            - used to combine the result of multiple futures.
            RETURNS:
                - result value when complete
                - unchecked exception if one of the futures throws an exceptoin
     */
    private static Integer joinDemo() {

        CompletableFuture<Integer> future1 = supplyAsync(() -> 50);
        CompletableFuture<Integer> future2 = supplyAsync(() -> 40);
        CompletableFuture<Integer> future3 = supplyAsync(() -> 30);

        Optional<Integer> maxElement = Stream.of(future1, future2, future3)
                // returns the stream of results of all futures.
                .map(CompletableFuture::join)
                .max(Integer::compareTo);

        return maxElement.orElse(0);
    }


    /*
        anyOf()
        - Similar to allOf(), but it doesn't wait for all of the CFs to complete.
        - it fails fast... as soon as any CFs complete w/ the same result, it returns a new CF.
     */
    private static Integer anyOfDemo() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = supplyAsync(() -> 50);
        CompletableFuture<Integer> future2 = supplyAsync(() -> 40);
        CompletableFuture<Integer> future3 = supplyAsync(() -> 30);

        // first completed future is going to be returned.
        CompletableFuture<Object> firstCompleted = anyOf(future1, future2, future3);

        return (Integer) firstCompleted.get();

    }

    @SuppressWarnings("SameParameterValue")
    private static int doWork(int length) {

        try {
            SECONDS.sleep(length);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println(currentThread().getName());

        return random.nextInt(100);

    }

}
