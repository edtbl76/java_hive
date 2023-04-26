package StructuringConcurrentApplications_2.CancellationAndShutdown_7.Examples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class TrackingExecutor  extends AbstractExecutorService {
    private final ExecutorService executor;
    private final Set<Runnable> tasksCancelledAtShutdown =
            Collections.synchronizedSet(new HashSet<>());

    public TrackingExecutor(ExecutorService executor) {
        this.executor = executor;
    }

    /*
        There is a race condition here that MAY yield false positives.
        - tasks may be identified as cancelled, but were actually completed.

        HOW?
        - the thread pool could be shutdown between
            - last instruction of the task executes
            - when pool records tasks as complete.
     */
    public List<Runnable> getCancelledTasks() {
        if (!executor.isTerminated()) {
            throw new IllegalStateException("Do exception thing");
        }
        return new ArrayList<>(tasksCancelledAtShutdown);
    }

    public void execute(final Runnable runnable) {
        executor.execute(() -> {
            try {
                runnable.run();
            } finally {
                if (isShutdown() && Thread.currentThread().isInterrupted()) {
                    tasksCancelledAtShutdown.add(runnable);
                }
            }
        });
    }

    @Override
    public void shutdown() {

    }

    @Override
    public List<Runnable> shutdownNow() {
        return null;
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

}
