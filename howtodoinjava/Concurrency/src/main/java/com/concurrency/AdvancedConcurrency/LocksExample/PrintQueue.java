package com.concurrency.AdvancedConcurrency.LocksExample;

import java.time.LocalDateTime;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {

    private final Lock queueLock = new ReentrantLock();

    public void printJob(Object document) {
        /*
            This is where all of the magic lives.
            - the PrintJob acquires the lock, does its critical work and then regardless of the outcome, it releases
            the lock.
         */
        queueLock.lock();
        try {
            Long duration = (long)(Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + ": PrintQueue: Printing a Job during " +
                    (duration/1000) + " seconds :: Time - " + LocalDateTime.now());
            Thread.sleep(duration);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            // This is the most important part.
            // If you forget to release the lock, then all of the Threads waiting for the resource will end up
            // in a DeadLock situation.
            System.out.printf("%s: Print complete\n", Thread.currentThread().getName());
            queueLock.unlock();
        }
    }
}
