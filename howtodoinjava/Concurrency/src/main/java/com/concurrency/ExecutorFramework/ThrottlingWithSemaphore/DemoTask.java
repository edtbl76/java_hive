package com.concurrency.ExecutorFramework.ThrottlingWithSemaphore;

public class DemoTask implements Runnable {

    private String name = null;
    public DemoTask(String name) {
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
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Executing: " + name);
    }
}
