package com.concurrency.DelayQueueExample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;

public class DelayedEventConsumer implements Runnable {
    private final DelayQueue<DelayedEvent> queue;

    public DelayedEventConsumer(DelayQueue<DelayedEvent> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        List<DelayedEvent> events = new ArrayList<>();
        queue.drainTo(events);
        System.out.println("\nEvent processing start: ");
        events.forEach(System.out::println);
        System.out.println("\nEvent processing end  : ");

    }
}
