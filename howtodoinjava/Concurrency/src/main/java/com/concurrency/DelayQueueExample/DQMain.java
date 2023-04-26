package com.concurrency.DelayQueueExample;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DQMain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        DelayQueue<DelayedEvent> queue = new DelayQueue<>();
        AtomicInteger counter = new AtomicInteger();

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(2);
        ses.scheduleAtFixedRate(new DelayedEventProducer(queue, counter), 1, 2, TimeUnit.SECONDS);
        ses.scheduleAtFixedRate(new DelayedEventConsumer(queue), 1, 10, TimeUnit.SECONDS);

    }
}
