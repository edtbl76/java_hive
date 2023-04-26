package com.concurrency.ExecutorFramework.BlockingQueue;

public class BQDemoTask implements Runnable {

    private String name = null;
    public BQDemoTask(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Executing: " + name);
    }
}
