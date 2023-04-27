package multithreading.threads.threadhandling;

import utils.Generated;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.awaitility.Awaitility.await;

@Generated
@SuppressWarnings("all")
public class ExecuteMe implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("Say Hello over and over again.");
            await().atMost(500, MILLISECONDS);
        }
    }
}

