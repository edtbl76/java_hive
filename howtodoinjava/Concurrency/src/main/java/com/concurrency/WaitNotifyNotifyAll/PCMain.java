package com.concurrency.WaitNotifyNotifyAll;

import java.util.ArrayList;
import java.util.List;

public class PCMain {

    public static void main(String[] args) {
        List<Integer> taskQueue = new ArrayList<>();
        int MAX_CAPACITY = 5;

        // Create and start the threads.
        Thread producer = new Thread(new Producer(taskQueue, MAX_CAPACITY), "Producer");
        Thread consumer = new Thread(new Consumer(taskQueue), "Consumer");
        producer.start();
        consumer.start();
    }
}

/*
    WHAT HAPPENS.

    producer starts producing right away, consumer starts in a blocking state.
    - producer will keep producing until the queue is full.
    - at this point the producer thread goes into a wait() state.
    - it adds its "payload" to the shared taskQueue and then calls notifyAll so that all of the other waiting threads can
    become action

    - the consumer gets the notification and begins pulling items off of the queue until it is empty.
    - it calls wait(), executes the extra code blocks and then calls notifyAll().

    this loops back to the producer.
 */
