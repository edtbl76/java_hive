package multithreading.examples;

import utils.Generated;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
    Missed Signals occur when a signal is sent by a thread before the other
    threads are waiting on a condition or predicate.
 */
@SuppressWarnings("all")
@Generated
public class MissedSignalExample {

    public static void example() throws InterruptedException {

        final ReentrantLock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();

        Thread signalThread = new Thread(() -> {
            lock.lock();
            condition.signal();
            System.out.println("Sent signal");
            lock.unlock();
        });

        Thread waitThread = new Thread(() -> {
            lock.lock();

            try {
                condition.await();
                System.out.println("Received signal");
            } catch (InterruptedException ignored) {
            }

            lock.unlock();
        });

        /*
            Signal thread sents a signal before waitThread starts.
         */
        signalThread.start();
        waitThread.start();

        signalThread.join();
        waitThread.join();

        System.out.println("Program exiting.");
    }

    public static void main(String[] args) throws InterruptedException {
        MissedSignalExample.example();
    }
}
