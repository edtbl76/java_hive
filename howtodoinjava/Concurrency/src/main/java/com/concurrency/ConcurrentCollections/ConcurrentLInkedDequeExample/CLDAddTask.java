package com.concurrency.ConcurrentCollections.ConcurrentLInkedDequeExample;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.IntStream;

public class CLDAddTask implements Runnable {

    private ConcurrentLinkedDeque<String> list;
    public CLDAddTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        IntStream.range(0, 10000).forEach(value -> list.add(name + ": Element " + value));
    }
}
