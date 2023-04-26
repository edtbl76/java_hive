package StructuringConcurrentApplications_2.CancellationAndShutdown_7.Examples;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

public class LogWriter {
    private final BlockingQueue<String> queue;
    private final LoggerThread logger;

    public LogWriter(BlockingQueue<String> queue, LoggerThread logger) {
        this.queue = queue;
        this.logger = logger;
    }

    public void start() {
        logger.start();
    }

    public void log(String message) throws InterruptedException {
       queue.put(message);
    }

    private class LoggerThread extends Thread {

        /*
            Stream classes like PrintWriter are thread safe.
            - this is a simple approach that requires no explicit synchronization

            NOTE:
                - if you are logging multiple lines as part of a single log message,
                additional client-side locking may be needed to prevent undesirable
                interleaving of output from multiple threads.

                EX:
                - if 2 threads log multiline stack traces to same stream
                    - (1 println() call per line)
                    - the results would be unpredictably interleaved.
                    - will look like shit.
         */
        private final PrintWriter printWriter;

        public LoggerThread(PrintWriter printWriter) {
            this.printWriter = printWriter;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    printWriter.println(queue.take());
                }
            } catch (InterruptedException ignored) {
            } finally {
                printWriter.close();
            }
        }
    }
}
