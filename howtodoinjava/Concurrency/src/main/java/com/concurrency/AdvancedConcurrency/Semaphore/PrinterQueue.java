package com.concurrency.AdvancedConcurrency.Semaphore;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
    This is the printqueue/printer
 */
public class PrinterQueue {

    // tracks no of printers used at any point in time.
    private final Semaphore semaphore;

    // this lock is used to guard the printers from being oversubscribed
    private final Lock printerLock;

    // represents pool of freeprinters.
    private boolean[] freePrinters;

    /*

     */
    public PrinterQueue() {
        // tracks the # of printers in use at any point in time.
        semaphore = new Semaphore(3);
        freePrinters = new boolean[3];

        /*
            This is easier than iterating through an old school for loop.
         */
        Arrays.fill(freePrinters, true);

        // this is used for locking the printer pool before checking/acquiring a printer from the printer pool.
        printerLock = new ReentrantLock();
    }

    /*
        - decrements the printer (i.e. we are marking one in use)
        - now we get one of the printers, complete the print  job and then releases the printer and semaphore.
     */
    public void printJob(Object document) {

        try {
            // decr. semaphore ctr to mark printer as busy
            semaphore.acquire();

            // get free printer
            int assigned = getPrinter();

            // print the job
            Long duration = (long)(Math.random() * 10000);
            System.out.println(Thread.currentThread().getName()
                    + ": Printer " + assigned
                    + ": Printing a Job during " + (duration/1000)
                    + " seconds :: Time - " + LocalDateTime.now());

            // free printer after job is done
            releasePrinter(assigned);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            System.out.printf("%s: The document has been printer\n",
                    Thread.currentThread().getName());

            // release the ctr (i.e. make a resource available)
            semaphore.release();
        }
    }

    // Acquire a free printer for printing a job
    private int getPrinter() {

        int foundPrinter = -1;
        try {
            // Get a lock so that only one thread can pass this point
            printerLock.lock();

            // find free printer
            for (int i = 0; i < freePrinters.length; i++) {

                // if free printer found mark busy (because we are about to use it)
                if (freePrinters[i]) {
                    foundPrinter = i;
                    freePrinters[i] = false;
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Allow other threads access to check for free printer
            printerLock.unlock();
        }
        return foundPrinter;
    }

    // Release the printer
    private void releasePrinter(int i) {
        printerLock.lock();

        // Mark the printer as free
        freePrinters[i] = true;
        printerLock.unlock();
    }

}
