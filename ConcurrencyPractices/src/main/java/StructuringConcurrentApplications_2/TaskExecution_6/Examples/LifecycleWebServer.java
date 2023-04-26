package StructuringConcurrentApplications_2.TaskExecution_6.Examples;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class LifecycleWebServer {

    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (!executor.isShutdown()) {
            try {
                final Socket connection = socket.accept();
                executor.execute(() -> handleRequest(connection));
            } catch (RejectedExecutionException e) {
                if (!executor.isShutdown()) {
                    System.out.println("Task Submission Rejected " + e);
                }
            }
        }
    }

    private void stop() {
        executor.shutdown();
    }

    private void handleRequest(Socket connection) {
        Request request = readRequest(connection);
        if (isShutdownRequest(request)) {
            stop();
        } else {
            dispatchRequest(request);
        }
    }

    // Bullshit below this point
    private void dispatchRequest(Request request) {
    }


    private boolean isShutdownRequest(Request request) {
        return Boolean.TRUE;
    }

    private Request readRequest(Socket connection) {
        return new Request();
    }
}
