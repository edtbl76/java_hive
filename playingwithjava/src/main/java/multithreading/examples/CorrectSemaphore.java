package multithreading.examples;

import utils.Generated;

import java.util.concurrent.Semaphore;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

@Generated
@SuppressWarnings("all")
public class CorrectSemaphore {

    public static void example() throws InterruptedException {

        final Semaphore semaphore = new Semaphore(1);

        Thread badThread = new Thread(() -> {
            while (true) {
                try {
                    semaphore.acquire();
                    try {
                        throw new RuntimeException("Bad Thread");
                    } catch (Exception e) {
                        // handle program logic
                        return;
                    } finally {
                        System.out.println("Bad thread releasing semaphore.");
                        /*
                            This is the proper impl.
                            - we need to release the semaphore in a finally block so
                            that it doesn't wait indefinitely.
                         */
                        semaphore.release();
                    }
                } catch (InterruptedException e) {
                    //handle
                }

            }
        });

        badThread.start();

        await().atMost(1, SECONDS);

        final Thread goodThread = new Thread(() -> {
            System.out.println("Good thread waiting to be signalled.");
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                // handle.
            }
        });

        goodThread.start();
        badThread.join();
        goodThread.join();
        System.out.println("Exiting");
    }

    public static void main(String[] args) throws InterruptedException {
        CorrectSemaphore.example();
    }
}
