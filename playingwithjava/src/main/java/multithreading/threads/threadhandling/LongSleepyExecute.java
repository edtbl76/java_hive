package multithreading.threads.threadhandling;

import utils.Generated;

import static java.lang.System.currentTimeMillis;

@Generated
@SuppressWarnings("all")
public class LongSleepyExecute implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("innerThread sleeps at " + currentTimeMillis() / 1000);
            Thread.sleep(1_000_000);
        } catch (InterruptedException e) {
            System.out.println("innerThread interrupted at " + currentTimeMillis() / 1000);
        }
    }
}
