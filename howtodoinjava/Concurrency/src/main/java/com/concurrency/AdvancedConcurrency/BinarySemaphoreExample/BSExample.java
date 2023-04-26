package com.concurrency.AdvancedConcurrency.BinarySemaphoreExample;

import java.util.stream.IntStream;

public class BSExample {

    public static void main(String[] args) {
        BSPrinterQueue printerQueue = new BSPrinterQueue();
        Thread[] thread = new Thread[10];

        IntStream.range(0,10).forEach(value -> thread[value] = new Thread(new BSPrintingJob(printerQueue), "Thread " + value));
        IntStream.range(0,10).forEach(value -> thread[value].start());
    }
}
