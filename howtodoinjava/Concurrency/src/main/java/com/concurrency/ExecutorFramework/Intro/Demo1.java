package com.concurrency.ExecutorFramework.Intro;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo1 {

    private static ExecutorService executorService = null;
    private static volatile Future taskOneResults = null;
    private static volatile Future taskTwoResults = null;

    /*
        There are two running tasks.
        - neither of the tasks is expected to terminate, and both tasks will run continuously until the app terminates.

        - if any task runs to completion, the app will notice and restart the task.
        - if any task throws an exception, the app will catch it and restart the task.
     */
    public static void main(String[] args) {
        executorService = Executors.newFixedThreadPool(2);

        // infinite loop.
        while (true) {

            //* If anything in checkTasks() breaks, we'll catch it, print it and then restart the task
            //  - checkTasks looks for null, Done or Cancelled states and restarts the thread under those conditions.
            try {
                checkTasks();
                Thread.sleep(1000);
            } catch (Exception ex) {
                System.err.println("Caught exception: " + ex.getMessage());
            }
        }
    }

    private static void checkTasks() throws Exception {
        if(taskOneResults == null || taskOneResults.isDone() || taskOneResults.isCancelled()) {
            taskOneResults = executorService.submit(new TestOne());
        }

        if(taskTwoResults == null || taskTwoResults.isDone() || taskTwoResults.isCancelled()) {
            taskTwoResults = executorService.submit(new TestTwo());
        }
    }
}

class TestOne implements Runnable {
    @Override
    public void run() {
        while(true) {
            System.out.println("Executing task one");
            try {
                Thread.sleep(1000);
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
    }
}

class TestTwo implements Runnable {
    @Override
    public void run() {
        while(true) {
            System.out.println("Executing task two");
            try {
                Thread.sleep(1000);
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
    }
}