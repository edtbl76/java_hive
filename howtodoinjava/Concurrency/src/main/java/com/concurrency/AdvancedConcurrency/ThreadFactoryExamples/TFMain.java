package com.concurrency.AdvancedConcurrency.ThreadFactoryExamples;

import java.util.stream.IntStream;

public class TFMain {

    public static void main(String[] args) {
        CustomThreadFactory factory = new CustomThreadFactory("CustomThreadFactory");
        TFTask task = new TFTask();
        System.out.printf("Starting threads\n\n");

        /*
            The original example sucked.
         */
        IntStream.range(1,11).forEach(value -> {
            factory.newThread(task).start();
        });
        System.out.printf("All threads have been created\n\n");
        System.out.println("CustomThreadFactory stats:\n\n" + factory.getStats());
    }
}
