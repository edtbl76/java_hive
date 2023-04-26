package com.concurrency.AdvancedConcurrency.ThreadLocalVariables;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class DemoTask implements Runnable {

    // atomic int containing the next thread ID to be assigned
    private static final AtomicInteger      nextId = new AtomicInteger(0);

    // ThreadLocal variable that contains each thread's id
    private static final ThreadLocal<Integer> threadid = ThreadLocal.withInitial(nextId::getAndIncrement);

    // return current thread's unique id (assigns if necessary)
    private int getThreadId() {
        return threadid.get();
    }

    // return current thread's start time.
    private static final ThreadLocal<LocalDateTime> startDate = new ThreadLocal<>() {
        protected LocalDateTime initialValue() {
            return LocalDateTime.now();
        }
    };

    @Override
    public void run() {
        System.out.printf("Starting Thread: %s : %s\n", getThreadId(), startDate.get());
        try {
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.printf("Thread Finished: %s : %s\n", getThreadId(), startDate.get());
    }
}
