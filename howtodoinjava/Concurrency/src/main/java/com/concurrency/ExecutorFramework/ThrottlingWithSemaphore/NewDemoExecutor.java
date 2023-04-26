package com.concurrency.ExecutorFramework.ThrottlingWithSemaphore;

import java.util.concurrent.*;

public class NewDemoExecutor {

    public static void main(String[] args) {

        Integer threadCounter = 0;
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(50);
        NewBlockingThreadPoolExecutor executor = new NewBlockingThreadPoolExecutor(10, 20, 5000,
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

                System.out.println("Adding another time: " + ((DemoTask) r).getName());
                executor.execute(r);
            }
        });

        executor.prestartAllCoreThreads();
        while (true) {
            threadCounter++;
            System.out.println("Adding DemoTask: " + threadCounter);
            executor.execute(new DemoTask(threadCounter.toString()));
            if (threadCounter == 1000)
                break;
        }
    }
}
