package com.concurrency.WaitNotifyNotifyAll;

import java.util.List;

public class Producer implements Runnable {
    private final List<Integer> taskQueue;
    private final int           MAX_CAPACITY;

    public Producer(List<Integer> sharedQueue, int size) {
        this.taskQueue = sharedQueue;
        this.MAX_CAPACITY = size;
    }

    @Override
    public void run() {
        int counter = 0;

        /*
            produce(counter++) is in an infinite loop
                - producer will keep producing elements at a regular interval
         */
        while (true) {
            try {
                produce(counter++);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    /*
        This is written following general wait() guidelines.
     */
    private void produce(int i) throws InterruptedException {
        synchronized (taskQueue) {
            while (taskQueue.size() == MAX_CAPACITY) {
                System.out.println("Queue is full " + Thread.currentThread().getName() + " is waiting, " +
                        "size: " + taskQueue.size());
                taskQueue.wait();
            }

            /*
                This is the "additional code" that executes when wait() is over.

                Producer thread PRODUCES a new resource every 1 second.
                - then adds it to taskQueue
                - then calls notifyAll()
             */
            Thread.sleep(1000);
            taskQueue.add(i);
            System.out.println("Produced: " + i);
            taskQueue.notifyAll();
        }
    }
}
