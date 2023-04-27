package multithreading.threads.countdownlatch;

import utils.Generated;

import java.util.concurrent.CountDownLatch;

@SuppressWarnings("all")
@Generated
public class Worker extends Thread {

    private CountDownLatch countDownLatch;

    public Worker(CountDownLatch countDownLatch, String name) {
        super(name);
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("Worker " + Thread.currentThread().getName() + " started");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Worker " + Thread.currentThread().getName() + " finished");

        // Each thread calls countDown Latch on task completion.
        countDownLatch.countDown();
    }
}
