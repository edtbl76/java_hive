package com.concurrency.AdvancedConcurrency.Semaphore;

/*
    Represents an "independent PrintJob"
        - this is an element of a printerQueue.
        - this element can be pulled off of the print queue by any printer.
        - implements Runnable interface so the printer can execute it when its turn comes.
 */
public class PrintingJob implements Runnable {
    private PrinterQueue printerQueue;

    public PrintingJob(PrinterQueue printerQueue) {
        this.printerQueue = printerQueue;
    }

    @Override
    public void run() {
        System.out.printf("%s: Printing a document\n",
                Thread.currentThread().getName());
        printerQueue.printJob(new Object());
    }
}


