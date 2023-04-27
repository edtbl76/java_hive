package multithreading.threads.threadhandling;

import utils.Generated;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

@Generated
public class SleepyExecute implements Runnable {


    @Override
    @SuppressWarnings("java:S106")
    public void run() {
        System.out.println("Hello. innerThread going sleepies");
        await().atMost(1, SECONDS);
    }
}
