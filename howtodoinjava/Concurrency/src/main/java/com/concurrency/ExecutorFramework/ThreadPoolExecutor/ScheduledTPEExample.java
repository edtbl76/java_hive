package com.concurrency.ExecutorFramework.ThreadPoolExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledTPEExample {

    /*
        NOTES:

        ScheduledFuture schedule()
            - Parameters:
                - task to be executed
                    - Runnable "ScheduledFuture" (command)
                    - Callable "ScheduledFuture"
                - the delay before it is executed
                - the unit of time for the 2nd parameter.

        ScheduledFuture scheduleAtFixedRate
            - Parameters
                - task to be executed (Runnable only)
                - delay before periods begin
                - period/interval
                - time unit for 2nd/3rd parameters.

                NOTE: if any execution of this task takes LONGER THAN ITS PERIOD
                    - subsequent executions may start late, and will not concurrently execute!

        ScheduledFuture scheduleWithFixedDelay
            - (Same parameters as scheduleAtFixedRate)
            - no matter HOW LONG the task takes, the delay is a fixed period.
     */
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(2);

        ScheduledTask task = new ScheduledTask("Repeat Task");
        System.out.println("Created: " + task.getName());
        executor.scheduleWithFixedDelay(task, 2, 2, TimeUnit.SECONDS);
    }
}
