package multithreading.theory;

import utils.Generated;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

@Generated
public class DemoDeadlock {

    private int counter = 0;
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    Runnable incrementer = () -> {
        for (int i = 0; i < 100; i++) {
            incrementCounter();
            System.out.println("Incrementing " + i);
        }
    };

    Runnable decrementer = () -> {
        for (int i = 0; i < 100; i++) {
            decrementCounter();
            System.out.println("Decrementing " + i);
        }
    };

    public void runTest() throws InterruptedException {
        Thread incrementerThread = new Thread(incrementer);
        Thread decrementerThread = new Thread(decrementer);

        incrementerThread.start();
        await().atMost(100, SECONDS);
        decrementerThread.start();

        incrementerThread.join();
        decrementerThread.join();

        System.out.println("Done: " + counter);
    }



    void incrementCounter() {
        synchronized (lock1) {
            System.out.println("Acquired lock1");
            await().atMost(100, SECONDS);

            synchronized (lock2) {
                counter++;
            }
        }
    }

    void decrementCounter() {
        synchronized (lock2) {
            System.out.println("Acquired lock2");
            await().atMost(100, SECONDS);

            synchronized (lock1) {
                counter--;
            }
        }
    }

    public static void main(String[] args) {
       DemoDeadlock deadlock = new DemoDeadlock();
       try {
           deadlock.runTest();
       } catch (InterruptedException ignored) {
       }
    }
}
