package multithreading.examples;

import utils.Generated;

import java.util.concurrent.Semaphore;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

@Generated
@SuppressWarnings("all")
public class IncorrectSemaphore {


    public static void example() throws InterruptedException {

        final Semaphore semaphore = new Semaphore(1);

        Thread badThread = new Thread(() -> {
            while (true) {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    //handle me
                }

                // This exception will cause the system to crash
                throw new RuntimeException("exception happens at runtime.");

                // Unreachable.
                //semaphore.release();
            }
        });

        badThread.start();
        await().atMost(1, SECONDS);

        final Thread goodThread = new Thread(() -> {
            System.out.println("Good thread waiting to be signalled.");
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                // handle me.
            }
        });

        goodThread.start();
        badThread.join();
        goodThread.join();

        System.out.println("Exiting program.");
    }

    public static void main(String[] args) throws InterruptedException {
        IncorrectSemaphore.example();
    }
}
