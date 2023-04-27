package multithreading.threads.threadhandling;

import utils.Generated;

@SuppressWarnings("all")
@Generated
public class SleepThreads {

    public static void main(String[] args) throws InterruptedException {
        SleepyExecute sleepyExecute = new SleepyExecute();
        Thread innerThread = new Thread(sleepyExecute);

        innerThread.start();
        innerThread.join();
        System.out.println("Main thread exiting.");
    }
}
