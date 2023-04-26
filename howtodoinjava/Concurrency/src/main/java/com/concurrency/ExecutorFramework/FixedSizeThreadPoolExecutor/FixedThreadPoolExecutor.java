package com.concurrency.ExecutorFramework.FixedSizeThreadPoolExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;

public class FixedThreadPoolExecutor {

    public static void main(String[] args) {
        /*
            This is creating an executor w/ a max # of threads of 4
                - if you send (# of tasks) > (# of threads), then the remaining tasks are blocked until there is a free
                thread to process them.

            NOTE:
                Executors class includes a newSingleThreadExecutor() method which is like an RxJava Single.
                    - this is an EXTREME case of a fixed size executor... JUST ONE.
                    - (this is useful in tasks that require serialization)

         */
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);

        /*
            This is just a more modern way of  iterating through a range of numbers.
            I build my "Task" class.
            - I "log" the event.
            - Then I execute the task.
         */
        IntStream.range(1, 11).forEach(i -> {
            Task task = new Task("Task" + i);
            System.out.println("A new task has been added: " + task.getName());
            executor.execute(task);
        });

        /*
            This ells me the size of my pool.
         */
        System.out.println("Max threads in pool " + executor.getMaximumPoolSize());
        /*
            This shuts down the executor, and executes all of the waiting tasks.
         */
        executor.shutdown();
    }
}
