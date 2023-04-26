package com.concurrency.WaitNotifyNotifyAll;

import java.util.List;

public class Consumer implements Runnable {

    private final List<Integer> taskQueue;

    public Consumer(List<Integer> sharedQueue) {
        this.taskQueue = sharedQueue;
    }

    @Override
    public void run() {
        // Infinite loop, so it will keep consuming threads whenever it finds something in the taskQueue
        while (true) {
            try {
                consume();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void consume() throws InterruptedException {
        synchronized (taskQueue) {
            while (taskQueue.isEmpty()) {
                System.out.println("Queue is empty " + Thread.currentThread().getName() + " is waiting, " +
                        "size: " + taskQueue.size());
                taskQueue.wait();
            }

            /*
                Extra code that occurs after wait() completes

                Consumer thread takes 1 second to process consumed resource from taskQueue
             */
            Thread.sleep(1000);
            int i = (Integer) taskQueue.remove(0);
            System.out.println("Consumer: " + i);
            taskQueue.notifyAll();
        }
    }
}
