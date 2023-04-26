package com.concurrency.ExecutorFramework.BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import java.util.concurrent.TimeUnit;

public class BQCustomTPE extends ThreadPoolExecutor {

    public BQCustomTPE(int corePoolSize, int maximumPoolSize,
                       long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        System.out.println("Exec beforeExecute()");
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        if ( t!= null)
            System.out.println("Exec ExceptionHandler");
        System.out.println("Exec afterExecute()");
    }
}
