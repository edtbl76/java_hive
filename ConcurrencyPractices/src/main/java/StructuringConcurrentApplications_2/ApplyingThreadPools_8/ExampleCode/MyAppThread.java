package StructuringConcurrentApplications_2.ApplyingThreadPools_8.ExampleCode;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyAppThread extends Thread {

    public static final String DEFAULT_NAME = "MyAppThread";
    private static volatile boolean debugLifecycle = false;
    private static final AtomicInteger CREATED = new AtomicInteger();
    private static final AtomicInteger ALIVE = new AtomicInteger();
    private static final Logger LOGGER = Logger.getAnonymousLogger();

    public MyAppThread(Runnable runnable) {
        this(runnable, DEFAULT_NAME);
    }

    public MyAppThread(Runnable runnable, String poolName) {
        super(runnable, poolName + "-" + CREATED.incrementAndGet());

/*
        // Show the anonymous function inside so that we can see the Thread.UncaughtExceptionHandler()
        setUncaughtExceptionHandler(
                new Thread.UncaughtExceptionHandler() {
                    public void uncaughtException(Thread thread, Throwable throwable) {
                        LOGGER.log(Level.SEVERE, "UNCAUGHT in thread " + thread.getName(), throwable);
                    }
                }
        );*/

        setUncaughtExceptionHandler(
                (thread, throwable) ->
                        LOGGER.log(Level.SEVERE, "UNCAUGHT in thread " + thread.getName(), throwable)
        );
    }

    @Override
    public void run() {
        // copy debug flag to ensure consistent value through out (DEFENSIVE COPY)
        boolean debug = debugLifecycle;
        if (debug) {
            LOGGER.log(Level.FINE, "Created " + getName());
        }

        try {
            ALIVE.incrementAndGet();
            super.run();
        } finally {
            ALIVE.decrementAndGet();
            if (debug) {
                LOGGER.log(Level.FINE, "Exiting " + getName());
            }
        }
    }

    public static int getThreadsCreated() {
        return CREATED.get();
    }

    public static int getThreadsAlive() {
        return ALIVE.get();
    }

    public static boolean getDebug() {
        return debugLifecycle;
    }

    public static void setDebug(boolean b) {
        debugLifecycle = b;
    }
}
