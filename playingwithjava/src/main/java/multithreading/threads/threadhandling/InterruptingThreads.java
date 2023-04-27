package multithreading.threads.threadhandling;

import utils.Generated;

import static java.lang.System.currentTimeMillis;


/*
    Demonstrates how to interrupt a misbhaving thread by interrupting it.
 */
@Generated
public class InterruptingThreads {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) throws InterruptedException {

        LongSleepyExecute sleepyExecute = new LongSleepyExecute();
        Thread innerThread = new Thread(sleepyExecute);

        innerThread.start();
        System.out.println("Main thread sleeps at " + currentTimeMillis() / 1000);
        Thread.sleep(5000);
        innerThread.interrupt();
        System.out.println("Main thread exits at " + currentTimeMillis() / 1000);
    }
}
