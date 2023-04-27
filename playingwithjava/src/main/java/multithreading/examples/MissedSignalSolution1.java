package multithreading.examples;

import utils.Generated;

import java.util.concurrent.Semaphore;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.awaitility.Awaitility.await;

@Generated
@SuppressWarnings("all")
public class MissedSignalSolution1 {

    public static void example() throws InterruptedException {

        final Semaphore semaphore = new Semaphore(1);

        Thread signalThread = new Thread(() -> {
            semaphore.release();
            System.out.println("Sent signal");
        });

        Thread waitThread = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("Acquired signal.");
            } catch (InterruptedException ignored) {}
        });

        signalThread.start();
        signalThread.join();

        await().atMost(5000, MILLISECONDS);

        waitThread.start();
        waitThread.join();

        System.out.println("Program exiting");

    }

    public static void main(String[] args) throws InterruptedException {
        MissedSignalSolution1.example();
    }
}
