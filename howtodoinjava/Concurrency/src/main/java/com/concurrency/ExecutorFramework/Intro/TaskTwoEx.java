package com.concurrency.ExecutorFramework.Intro;

public class TaskTwoEx implements Runnable {
    @Override
    public void run() {
        System.out.println("Executing Task Two");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
