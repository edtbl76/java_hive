package com.concurrency.AdvancedConcurrency.LocksExample;

import java.util.stream.IntStream;

public class LockExample {
    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread[] thread = new Thread[10];

        IntStream.range(0, 10).forEach(
                value -> thread[value] = new Thread(new PrintJob(printQueue), "Thread " + value)
        );

        IntStream.range(0, 10).forEach(value -> thread[value].start());
    }
}
