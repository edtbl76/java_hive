package com.concurrency.ExecutorFramework.Intro;

public class TaskOneEx implements Runnable {
    @Override
    public void run() {
        System.out.println("Executing Task One");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
