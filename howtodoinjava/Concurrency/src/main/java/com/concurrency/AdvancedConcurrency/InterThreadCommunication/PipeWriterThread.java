package com.concurrency.AdvancedConcurrency.InterThreadCommunication;

import java.io.PipedWriter;

public class PipeWriterThread implements Runnable {

    PipedWriter pw;
    String name = null;

    public PipeWriterThread(String name, PipedWriter pw) {
        this.name = name;
        this.pw = pw;
    }

    @Override
    public void run() {
        try {
            while (true) {

                // Write some data ever 2 seconds
                pw.write("Testing data written...\n");
                pw.flush();
                Thread.sleep(2000);
            }
        } catch (Exception ex) {
            System.out.println(" PipeThread Exception: " + ex);
        }
    }
}
