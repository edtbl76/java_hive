package StructuringConcurrentApplications_2.ApplyingThreadPools_8.ExampleCode;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class TimingThreadPool extends ThreadPoolExecutor {

    private final ThreadLocal<Long> START_TIME = new ThreadLocal<>();
    private final Logger LOGGER = Logger.getLogger("TimingThreadPool");
    private final AtomicLong NUMBER_OF_TASKS = new AtomicLong();
    private final AtomicLong TOTAL_TIME = new AtomicLong();

    public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                            BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        LOGGER.fine(String.format("Thread %s: start %s ", t, r));
        START_TIME.set(System.nanoTime());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        try {
           long endTime = System.nanoTime();
           long taskTime = endTime - START_TIME.get();
           NUMBER_OF_TASKS.incrementAndGet();
           TOTAL_TIME.addAndGet(taskTime);
           LOGGER.fine(String.format("Thread %s: end %s, time=%dns", t, r, taskTime));
        } finally {
            super.afterExecute(r, t);
        }
    }

    @Override
    protected void terminated() {
        try {
            LOGGER.fine(String.format("Terminated: avd time=%dns", TOTAL_TIME.get() / NUMBER_OF_TASKS.get()));
        } finally {
            super.terminated();
        }
    }
}
