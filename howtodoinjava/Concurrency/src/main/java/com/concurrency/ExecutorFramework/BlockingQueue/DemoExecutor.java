package com.concurrency.ExecutorFramework.BlockingQueue;


import java.util.concurrent.*;

public class DemoExecutor {

    public static void main(String[] args) {

        Integer threads = 0;
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(50);

        BQCustomTPE executor = new BQCustomTPE(10, 20, 5000,
                TimeUnit.MILLISECONDS, blockingQueue);

        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("DemoTask Rejected: " + ((BQDemoTask) r).getName());
                System.out.println("Waiting for a secoond.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.println("Add another: " + ((BQDemoTask) r).getName());
                executor.execute(r);
            }
        });

        // Start all threads
        executor.prestartAllCoreThreads();
        while (true) {
            threads++;
            System.out.println("Adding DemoTask: " + threads);
            executor.execute(new BQDemoTask(threads.toString()));

            if(threads == 100)
                break;
        }
    }
}
