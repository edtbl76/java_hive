package com.concurrency.ExecutorFramework.ThreadPoolExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

@SuppressWarnings("unused")
public class CustomThreadPool {

    private final int poolSize;
    private final WorkerThread[]  workers;
    private final LinkedBlockingQueue<Runnable> queue;

    public CustomThreadPool(int poolSize) {
        this.poolSize = poolSize;
        queue = new LinkedBlockingQueue<>();
        workers = new WorkerThread[poolSize];

        IntStream.range(1, poolSize).forEach(i -> {
            workers[i] = new WorkerThread();
            workers[i].start();
        });
    }

    public void execute(Runnable task) {
        synchronized (queue) {
            queue.add(task);
            queue.notify();
        }
    }

    private class WorkerThread extends Thread {
        @Override
        public void run() {
            Runnable task;

            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException ex) {
                            System.out.println("An error occurred while queue waits: " + ex.getMessage());
                        }
                    }
                    task = (Runnable) queue.poll();
                }

                try {
                    task.run();
                } catch (RuntimeException ex) {
                    System.out.println("Thread pool is interrupted: " + ex.getMessage());
                }
            }
        }
    }

    public void shutdown() {
        System.out.println("Shutting down thread pool");
        IntStream.range(1, poolSize).forEach(i -> workers[i] = null);
    }
}
