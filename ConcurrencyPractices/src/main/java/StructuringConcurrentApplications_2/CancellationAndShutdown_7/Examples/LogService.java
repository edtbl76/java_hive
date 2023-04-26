package StructuringConcurrentApplications_2.CancellationAndShutdown_7.Examples;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

public class LogService {

    private final BlockingQueue<String> queue;
    private final LoggerThread loggerThread;
    private final PrintWriter printWriter;

    public LogService(BlockingQueue<String> queue, LoggerThread loggerThread, PrintWriter printWriter) {
        this.queue = queue;
        this.loggerThread = loggerThread;
        this.printWriter = printWriter;
    }

    // Guarded
    private boolean isShutdown;
    private int reservations;

    public void start() {
        loggerThread.start();
    }

    public void stop() {
        synchronized (this) {
            isShutdown = true;
            loggerThread.interrupt();
        }
    }

    public void log(String message) throws InterruptedException {
        synchronized (this) {
            if (isShutdown) {
                throw new IllegalStateException("Do Exceptiony Thing");
            }
            ++reservations;
        }
        queue.put(message);
    }

    private class LoggerThread extends Thread {
        @Override
        public void run() {
            try {
                while(true) {
                    try {
                        synchronized (LogService.this) {
                            if (isShutdown && reservations == 0) {
                                break;
                            }
                        }
                        String message = queue.take();
                        synchronized (LogService.this) {
                            --reservations;
                        }
                        printWriter.println(message);
                    } catch (InterruptedException e) {
                       /*
                        Do some fancy retry shit.
                        */
                    }
                }
            } finally {
                printWriter.close();
            }
        }
    }
}
