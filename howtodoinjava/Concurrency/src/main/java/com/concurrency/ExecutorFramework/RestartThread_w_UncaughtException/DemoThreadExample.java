package com.concurrency.ExecutorFramework.RestartThread_w_UncaughtException;

public class DemoThreadExample {

    public static void main(String[] args) {
        TaskExample te = new TaskExample();
        Thread thread = new Thread(te);
        thread.start();
    }
}
