package com.concurrency.ExecutorFramework.FixedSizeThreadPoolExecutor;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {
    private String name;
    public Task(String name)  {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Long duration = (long)(Math.random() * 10);
            System.out.println("Doing the needful: " + name);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
