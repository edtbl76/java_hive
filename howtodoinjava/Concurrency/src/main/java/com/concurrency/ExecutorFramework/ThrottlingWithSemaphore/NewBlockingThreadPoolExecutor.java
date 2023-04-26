package com.concurrency.ExecutorFramework.ThrottlingWithSemaphore;

import java.util.concurrent.*;

/*
   The magic for this example lives in the TPE, where the Semaphore has been implemented.


 */
public class NewBlockingThreadPoolExecutor extends ThreadPoolExecutor {

    private final Semaphore semaphore;

    public NewBlockingThreadPoolExecutor(int corePoolSize, int maxPoolSize, long keepAliveTime, TimeUnit unit,
                                         BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maxPoolSize, keepAliveTime, unit, workQueue);
        semaphore = new Semaphore(corePoolSize + 50);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
    }

    @Override
    public void execute(final Runnable task) {

        boolean acquired = false;
        do {
            try {
                semaphore.acquire();
                acquired = true;
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        } while (!acquired);

        try {
            super.execute(task);
        } catch (final RejectedExecutionException ex) {
            System.out.println("Task Rejected, dude.");
            semaphore.release();
            throw ex;
        }
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        if (t != null)
            t.printStackTrace();
        semaphore.release();
    }
}
