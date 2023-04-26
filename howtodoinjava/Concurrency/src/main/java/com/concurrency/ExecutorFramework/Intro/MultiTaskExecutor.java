package com.concurrency.ExecutorFramework.Intro;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultiTaskExecutor {

    /*
        Sometimes, we have to perform multiple jobs in a single thread and each job is an instance of Runnable.
        This example Demonstrates the use of a multi-runnable.

        MULTIRUNNABLE
            - collection of runnables which need to be executed.

        the classes TaskOneEx -> TaskThreeEx are just tasks that need to be executed in a single thread.

        MultiRunnable is a wrapper to collect multiple runnables, and it overrides run() by iterating over the collection


     */
    public static void main(String[] args) {

        // A blocking queue is necessary because we are dealing with objects that require concurrency protection.
        // It is also necessary for the ThreadPool executor
        BlockingQueue<Runnable> worksQueue = new ArrayBlockingQueue<>(10);

        // The execution handler is the handler we pass to the ThreadPoolExecutor to determine what should happen when
        // thread exec fails.
        RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandelerImpl();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 10,
                TimeUnit.SECONDS, worksQueue, rejectedExecutionHandler);

        // This is pre-warming the thread pool.
        executor.prestartAllCoreThreads();

        // Building up a taskGroup (which is a List<Runnable>)
        List<Runnable> taskGroup = new ArrayList<>();
        taskGroup.add(new TaskOneEx());
        taskGroup.add(new TaskTwoEx());
        taskGroup.add(new TaskThreeEx());

        // This adds the collection to the queue as a MultiRunnable.
        worksQueue.add(new MultiRunnable(taskGroup));

    }
}

class RejectedExecutionHandelerImpl implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
        System.out.println(runnable.toString() + " : I've been rejected!");
    }
}
