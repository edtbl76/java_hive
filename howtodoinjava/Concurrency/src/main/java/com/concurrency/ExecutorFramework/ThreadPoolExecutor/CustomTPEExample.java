package com.concurrency.ExecutorFramework.ThreadPoolExecutor;

import java.util.stream.IntStream;

public class CustomTPEExample {

    public static void main(String[] args) {
        CustomThreadPool ctp = new CustomThreadPool(2);

        IntStream.range(1, 6).forEach(i -> {
            Task task = new Task("Task" + i);
            System.out.println("Created: " + task.getName());
            ctp.execute(task);
        });
    }
}
