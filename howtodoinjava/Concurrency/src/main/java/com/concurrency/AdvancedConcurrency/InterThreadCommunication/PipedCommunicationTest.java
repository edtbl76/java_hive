package com.concurrency.AdvancedConcurrency.InterThreadCommunication;

import java.io.PipedReader;
import java.io.PipedWriter;

public class PipedCommunicationTest {

    public static void main(String[] args) {
        new PipedCommunicationTest();
    }

    public PipedCommunicationTest() {
        try {
            // create writer/reader instances
            PipedReader pr = new PipedReader();
            PipedWriter pw = new PipedWriter();

            // connect the writer w/ the reader (using the method version)
            pw.connect(pr);

            // Create one writer thread and one reader thread.
            Thread readerThread = new Thread(new PipeReaderThread("ReaderThread", pr));
            Thread writerThread = new Thread(new PipeWriterThread("WriterThread", pw));

            // Start'em up
            readerThread.start();
            writerThread.start();
        } catch (Exception ex) {
            System.out.println("PipeThread Exception: " + ex);
        }
    }
}
