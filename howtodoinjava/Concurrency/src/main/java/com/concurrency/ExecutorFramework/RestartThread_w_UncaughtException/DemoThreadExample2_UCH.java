package com.concurrency.ExecutorFramework.RestartThread_w_UncaughtException;

public class DemoThreadExample2_UCH {
    public static void main(String[] args) {
        // UCH = Uncaught Exception Handler
        TaskUCH task = new TaskUCH();

        // Handling our business logic to the Thread instance
        Thread thread = new Thread(task);

        // execute!
        thread.start();

        /*
            The benefit of using the UncaughtExceptionHandler is that the thread doesn't stop.
            We'll see errors for the pieces that aren't working, and those won't get processed,
            but everything else will continue to work.
         */

    }
}
