package multithreading.theory;

import lombok.Generated;

import java.util.Random;

import static java.lang.System.*;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

@Generated
public class DemoThreadUnsafe {

    // Random sleep Example
    static Random random = new Random(currentTimeMillis());

    public static void sleepRandomly() {
        // Better tool than Thread.sleep()
        await().atMost(random.nextInt(10), SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {

        // Create object of unsafe counter.
        ThreadUnsafeCounter unsafeCounter = new ThreadUnsafeCounter();

        // Thread1
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                unsafeCounter.increment();
                DemoThreadUnsafe.sleepRandomly();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                unsafeCounter.decrement();
                DemoThreadUnsafe.sleepRandomly();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Counter is: " + unsafeCounter.getCount());
    }
}

@Generated
class ThreadUnsafeCounter {
    int count = 0;

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    int getCount() {
        return count;
    }
}