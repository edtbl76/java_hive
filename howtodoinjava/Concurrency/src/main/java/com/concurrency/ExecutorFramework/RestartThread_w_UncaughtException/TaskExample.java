package com.concurrency.ExecutorFramework.RestartThread_w_UncaughtException;

public class TaskExample implements Runnable {
    @Override
    public void run() {
        System.out.println(Integer.parseInt("123"));
        System.out.println(Integer.parseInt("456"));
        System.out.println(Integer.parseInt("789"));
        // This will cause a NumberFormatException
        System.out.println(Integer.parseInt("XYZ"));
        System.out.println(Integer.parseInt("123"));
    }
}
