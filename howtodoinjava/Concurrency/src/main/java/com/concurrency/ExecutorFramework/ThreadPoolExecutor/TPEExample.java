package com.concurrency.ExecutorFramework.ThreadPoolExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;

public class TPEExample {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        IntStream.range(1, 6).forEach(i -> {
            Task task = new Task("Task " + i);
            System.out.println("Created Task: " + task.getName());
            executor.execute(task);
        });
        executor.shutdown();
    }
}
