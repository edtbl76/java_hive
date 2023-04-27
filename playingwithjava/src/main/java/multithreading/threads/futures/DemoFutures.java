package multithreading.threads.futures;


import utils.Generated;

import java.util.concurrent.*;

import static java.util.concurrent.Executors.newFixedThreadPool;
import static utils.Utils.summation;

@Generated
@SuppressWarnings("all")
public class DemoFutures {

    /*
        Thread Pools impl'ing the ExecutorService return a future
        for their task submission methods.


     */
    static ExecutorService threadPool = newFixedThreadPool(2);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("sum: " + findSum(10));
        threadPool.shutdown();
    }

    static int findSum(final int number) throws ExecutionException, InterruptedException {
        Callable<Integer> sumTask = () -> summation(number);

        // submitting from threadpool returns a future
        Future<Integer> future = threadPool.submit(sumTask);

        /*
            get() is the method on the future that allows us to retrieve the data
            from the future.
         */
        return future.get();
    }

}
