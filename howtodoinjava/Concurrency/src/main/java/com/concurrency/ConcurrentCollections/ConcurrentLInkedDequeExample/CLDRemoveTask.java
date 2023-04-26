package com.concurrency.ConcurrentCollections.ConcurrentLInkedDequeExample;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.IntStream;

public class CLDRemoveTask implements Runnable {

    private ConcurrentLinkedDeque<String> list;

    public CLDRemoveTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        IntStream.range(0,5000).forEach(value -> {
            list.pollFirst();
            list.pollLast();
        });
    }
}
