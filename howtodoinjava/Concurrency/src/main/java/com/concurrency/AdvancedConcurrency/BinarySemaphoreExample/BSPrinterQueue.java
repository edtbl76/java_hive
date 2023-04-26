package com.concurrency.AdvancedConcurrency.BinarySemaphoreExample;

import java.time.LocalDateTime;
import java.util.concurrent.Semaphore;

public class BSPrinterQueue {

    private final Semaphore semaphore;

    // We are setting up a semaphore that only allows a single thread.
    public BSPrinterQueue() {
        semaphore = new Semaphore(1);
    }

    public void printJob(Object document) {

        try {
            semaphore.acquire();

            // fake print job
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + ": PrintQueue: Printing a Job during " +
                    (duration/1000) + " seconds :: Time - " + LocalDateTime.now());
            Thread.sleep(duration);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            System.out.printf("%s: Done\n", Thread.currentThread().getName());
            semaphore.release(); // necessary to prevent deadlock.
        }
    }
}
