package multithreading.threads.countdownlatch;

import utils.Generated;

import java.util.concurrent.CountDownLatch;

@Generated
public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Create CDL for 2 threads
        CountDownLatch countDownLatch = new CountDownLatch(2);

        // Create and start those 2 threads
        Worker workerA = new Worker(countDownLatch, "A");
        Worker workerB = new Worker(countDownLatch, "B");

        workerA.start();
        workerB.start();
        countDownLatch.countDown();

        // When the threads complete their tasks, they are returned
        // (the counter reaches 0)
        countDownLatch.await();

        // Execute Master Thread.
        Master master = new Master("Master Executed");
        master.start();
    }
}
