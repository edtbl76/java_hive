package com.concurrency.ConcurrentCollections.ConcurrentLInkedDequeExample;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.IntStream;

public class CLDMain {

    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
        Thread[] threads = new Thread[100];

        /*
            We add 100 CLDAddTasks tasks elements to "list"
            - each one of these tasks inserts 10k elements
         */
        IntStream.range(0, threads.length).forEach(value -> {
            threads[value] = new Thread(new CLDAddTask(list));
            threads[value].start();
        });

        // We output the number of threads (i.e. we have added 100 threads)
        System.out.printf("Main: %d AddTask threads have been launched\n", threads.length);

        /*
            Here we join the values so that we get the total (should be 1,000,000)
         */
        IntStream.range(0, threads.length).forEach(value -> {
            try {
                threads[value].join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        System.out.printf("Main: Size of List: %d\n", list.size());


        /*
            This repeats the logic from the AddTask block above, but here we are removing the tasks from the list.
         */
        IntStream.range(0, threads.length).forEach(value -> {
            threads[value] = new Thread(new CLDRemoveTask(list));
            threads[value].start();
        });
        System.out.printf("Main: %d RemoveTask threads have been launched\n", threads.length);


        /*
            Joining the results should show that we have 0 elements, because we have removed them all.
         */
        IntStream.range(0, threads.length).forEach(value -> {
            try {
                threads[value].join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        System.out.printf("Main: Size of List: %d\n", list.size());
    }
}
