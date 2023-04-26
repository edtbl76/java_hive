package com.concurrency.ExecutorFramework.TaskSchedulingWithExecutors;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorExample {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        TaskEx task1 = new TaskEx("Demo Task 1");
        TaskEx task2 = new TaskEx("Demo Task 2");

        System.out.println("The time is: " + LocalDateTime.now());

        executor.schedule(task1, 5, TimeUnit.SECONDS);
        executor.schedule(task2, 10, TimeUnit.SECONDS);

        // I set a 20 second wait to shut this bad boy down. Usually it would be much longer.. (depending on the
        // appropriate time for the workload to complete)
        try {
            executor.awaitTermination(20, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        /*
            this ends the program
            - However, the default behavior is to execute any waiting tasks at this point.
            - This can be overridden if you are using ScheduledExecutor. (NOT ScheduledExecutorService)
         */
        executor.shutdown();
    }
}
