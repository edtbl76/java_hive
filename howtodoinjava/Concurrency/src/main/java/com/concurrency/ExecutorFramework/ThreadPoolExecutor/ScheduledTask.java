package com.concurrency.ExecutorFramework.ThreadPoolExecutor;

import java.time.LocalTime;

public class ScheduledTask implements Runnable {

    private String name;

    public ScheduledTask(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        System.out.println("Executing : " + name + ", Current Seconds: " + LocalTime.now().getSecond());
    }
}
