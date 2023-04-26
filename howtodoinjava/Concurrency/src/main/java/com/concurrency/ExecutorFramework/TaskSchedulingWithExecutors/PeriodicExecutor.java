package com.concurrency.ExecutorFramework.TaskSchedulingWithExecutors;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class PeriodicExecutor {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        TaskEx task1 = new TaskEx("Demo Task 1");

        System.out.println("The time is now: " + LocalDateTime.now());

        // This executor is run periodically. (i.e. scheduleAtFixedRate)
        /*
            Four params
                - task to execute periodically
                - delay of time until first execution of the task
                - period for future executions
                    - MAKE  SURE THE TASK CAN EXECUTE IN THIS TIME.
                - time unit for  2nd and 3rd params.

         */
        ScheduledFuture<?> result = executor.scheduleAtFixedRate(task1,  2, 5, TimeUnit.SECONDS);

        try {
            TimeUnit.MILLISECONDS.sleep(20000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        executor.shutdown();
    }
}
