package StructuringConcurrentApplications_2.CancellationAndShutdown_7.Examples;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public interface CancellableTask<T> extends Callable<T> {

    void cancel();

    RunnableFuture<T> newTask();
}

class CancellingExecutor extends ThreadPoolExecutor {

    public CancellingExecutor(
            int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
            BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    /*
        By overriding newTaskFor from ThreadPoolExecutor, we
        allow a CancellableTask to return a RunnableFuture
     */
    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        if (callable instanceof CancellableTask) {
            return ((CancellableTask<T>) callable).newTask();
        }
        return super.newTaskFor(callable);
    }
}

abstract class SocketUsingTask<T> implements CancellableTask<T> {

    private Socket socket;

    protected synchronized void setSocket(Socket s) {
        socket = s;
    }

    @Override
    public void cancel() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ignored) {}
    }

    /*
        This is a concrete impl of the newTask() in CancellableTask

        Similar to previous examples

        - The try block closes the socket, using the impl of cancel defined above.
        - the finally block is the cleanup crew. It pulls the cancel() method from
        CancellableTask (which also pulls it from Callable))

     */
    @Override
    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                try {
                    SocketUsingTask.this.cancel();
                } finally {
                    return super.cancel(mayInterruptIfRunning);
                }
            }
        };
    }
}
