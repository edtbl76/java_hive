package com.concurrency.AdvancedConcurrency.BinarySemaphoreExample;

public class BSPrintingJob implements Runnable {

    private BSPrinterQueue printerQueue;

    public BSPrintingJob(BSPrinterQueue printerQueue) {
        this.printerQueue = printerQueue;
    }

    @Override
    public void run() {
        System.out.printf("%s: Printing...\n",
                Thread.currentThread().getName());
        printerQueue.printJob(new Object());
    }
}
