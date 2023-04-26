package com.concurrency.ExecutorFramework.TaskSchedulingWithExecutors;

import java.time.LocalDate;
import java.util.Date;

public class TaskEx implements Runnable {

    private String name;
    public TaskEx(String name) {
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
            System.out.println("Doing the needful: " + name + " - Time - " + new Date());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
