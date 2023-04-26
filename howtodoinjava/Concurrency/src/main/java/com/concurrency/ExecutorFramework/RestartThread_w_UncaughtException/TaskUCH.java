package com.concurrency.ExecutorFramework.RestartThread_w_UncaughtException;

public class TaskUCH implements Runnable {
    @Override
    public void run() {
        Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
        System.out.println(Integer.parseInt("123"));
        System.out.println(Integer.parseInt("456"));
        System.out.println(Integer.parseInt("789"));
        System.out.println(Integer.parseInt("XYZ"));
        System.out.println(Integer.parseInt("123"));
    }
}
