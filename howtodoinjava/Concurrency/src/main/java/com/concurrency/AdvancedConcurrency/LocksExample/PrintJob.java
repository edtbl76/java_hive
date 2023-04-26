package com.concurrency.AdvancedConcurrency.LocksExample;

public class PrintJob implements Runnable {

    private PrintQueue printQueue;

    public PrintJob(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.printf("%s: Printing Document\n", Thread.currentThread().getName());
        printQueue.printJob(new Object());
    }
}
