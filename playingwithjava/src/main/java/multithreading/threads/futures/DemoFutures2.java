package multithreading.threads.futures;


import utils.Generated;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.util.concurrent.Executors.newFixedThreadPool;

/*
    Demonstrates how ThreadPools, Callables and Futures are used w/ Exceptions.
 */
@Generated
@SuppressWarnings("all")
public class DemoFutures2 {

    static ExecutorService threadPool = newFixedThreadPool(2);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("sum: " + findSumWithException(10));
        threadPool.shutdown();
    }

    static int findSumWithException(final int number) throws ExecutionException, InterruptedException {

        int result = -1;

        Callable<Integer> sumTask = () -> fakeSummation(number);

        Future<Integer> future = threadPool.submit(sumTask);

        /*
            get() is the method on the future that allows us to retrieve the data
            from the future.
         */

        try {
            result = future.get();
        } catch (ExecutionException ee) {
            System.out.println("Shit Done Happened: " + ee.getCause());
        }
        return result;
    }

    public static int fakeSummation(int number) {
        throw new RuntimeException("Awww Sheeyut!");
    }

}
