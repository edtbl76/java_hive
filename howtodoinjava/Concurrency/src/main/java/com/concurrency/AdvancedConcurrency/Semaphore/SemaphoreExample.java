package com.concurrency.AdvancedConcurrency.Semaphore;

import java.util.stream.IntStream;

public class SemaphoreExample {

    public static void main(String[] args) {
        // Create a printqueue instance
        PrinterQueue printerQueue = new PrinterQueue();

        // create a pool of 10 threads.
        Thread[] thread = new Thread[10];

        // create a printjob for each thread.
        IntStream.range(0, 10).forEach(
                value -> thread[value] = new Thread(new PrintingJob(printerQueue), "Thread " + value)
        );

        // start each thread.
        IntStream.range(0, 10).forEach(
                value -> thread[value].start()
        );

    }
}
