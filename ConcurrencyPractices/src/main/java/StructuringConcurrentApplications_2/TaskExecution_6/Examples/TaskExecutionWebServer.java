package StructuringConcurrentApplications_2.TaskExecution_6.Examples;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TaskExecutionWebServer {
    private static final int NTHREADS = 100;
    private static final Executor executor = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);

/*        // Anonymous function shown for posterity.
        while (true) {
            final Socket connection = socket.accept();
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    handleRequest(connection);
                }
            };
            executor.execute(task);
        }*/

        while (true) {
            final Socket connection = socket.accept();
            executor.execute(() -> handleRequest(connection));
        }


    }

    private static void handleRequest(Socket connection) {
    }
}
