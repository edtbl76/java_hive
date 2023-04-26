package com.concurrency.ExecutorFramework.CallableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;

public class CallableExample {

    public static void main(String[] args) {
        // Create a Fixed Thread Pool w/ 2 threads.
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        // List of Future<Integer>s.
        List<Future<Integer>> resultList = new ArrayList<>();

        // Setup a random number
        Random random = new Random();

        // We are going to loop through 4 calls.
        /*
            - We create a random number fromm 1 - 10
            - Then we create the new Callable object (initializing its call() method by passing the random number)
            - we hand the Callable to the executor using submit()
            - we add the iteration's result to our total resultList.
         */
        IntStream.range(0, 4).forEach(i -> {
            Integer number = random.nextInt(10);
            FactorialCalculator calculator = new FactorialCalculator(number);
            Future<Integer> result = executor.submit(calculator);
            resultList.add(result);
        });

        /*
            Now we are iterating throuugh the result list, which is a collection of Future<Integer>
            - future.get() is how we obtain the result of the call() method within the callable.
            - isDone() is a boolean that tells us that this particular execution has been completed.
                - this can be used to implement a check, which may/may not enter a resiliency pipeline.
         */
        for(Future<Integer> future : resultList) {
            try {
                System.out.println("Future result is - " + " - " + future.get() + "; And Task done is "
                + future.isDone());
            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
        }

        // This kills the executor.
        executor.shutdown();
    }
}
