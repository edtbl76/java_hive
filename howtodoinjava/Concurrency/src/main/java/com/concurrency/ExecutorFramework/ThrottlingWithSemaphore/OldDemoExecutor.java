package com.concurrency.ExecutorFramework.ThrottlingWithSemaphore;

import java.util.concurrent.*;

public class OldDemoExecutor {

    /*
        Since the work queue  is larger than the maxPoolSize we end up trying to add jobs to the executor when it is full.
        THis results in a rejection.


     */
    public static void main(String[] args) {
        Integer threadCounter = 0;
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(50);
        OldCustomThreadPoolExecutor executor = new OldCustomThreadPoolExecutor(10, 20, 5000,
                TimeUnit.MILLISECONDS, blockingQueue);
        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("DemoTask Rejected: " + ((DemoTask) r).getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.println("Lets add another time: " + ((DemoTask) r).getName());
                executor.execute(r);
            }
        });

        // Start all core threads initially
        executor.prestartAllCoreThreads();

        while(true) {
            threadCounter++;

            // add threads 1 at a time.
            System.out.println("Adding DemoTask: " + threadCounter);
            executor.execute(new DemoTask(threadCounter.toString()));
            if (threadCounter == 1000)
                break;
        }
    }
}
