package com.concurrency.AdvancedConcurrency.InterThreadCommunication;

import java.io.PipedReader;

public class PipeReaderThread implements Runnable {

    PipedReader pr;
    String name = null;

    public PipeReaderThread(String name, PipedReader pr) {
        this.name = name;
        this.pr = pr;
    }

    @Override
    public void run() {
        try {
            // keep reading data from stream and dump it to STDOUT
            while (true) {
                char c = (char) pr.read();

                // check for EOF (-1)
                if (c != -1) {
                    System.out.print(c);
                }
            }
        } catch (Exception ex) {
            System.out.println(" PipeThread Exception: " + ex);
        }
    }
}

