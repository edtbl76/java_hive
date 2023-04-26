package StructuringConcurrentApplications_2.TaskExecution_6.Examples;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadPerTaskWebServer {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);

/*
        This is what the anonymous function looks like. Just so you can see how it works.

        while (true) {
            final Socket connection = socket.accept();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    handleRequest(connection);
                }
            };
            new Thread(runnable);
        }*/

        while (true) {
            final Socket connection = socket.accept();
            new Thread(() -> handleRequest(connection));
        }
    }

    private static void handleRequest(Socket connection) {
    }
}
