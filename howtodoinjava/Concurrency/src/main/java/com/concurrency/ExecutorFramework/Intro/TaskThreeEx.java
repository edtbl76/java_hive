package com.concurrency.ExecutorFramework.Intro;

public class TaskThreeEx implements Runnable {
    @Override
    public void run() {
        System.out.println("Executing Task Three");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
