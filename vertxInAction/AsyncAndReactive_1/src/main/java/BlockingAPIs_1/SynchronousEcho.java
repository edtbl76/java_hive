package BlockingAPIs_1;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SynchronousEcho {
    public static void main(String[] args) throws Throwable {

        // Create a socket and bind it to a TCP port.
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(3000));

        // Create an endless loop that creates a new thread when there is a socket connection.
        while (true) {
            Socket socket = server.accept();
            new Thread(clientHandler(socket)).start();
        }
    }

    private static Runnable clientHandler(Socket socket) {
        return () -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) {

                String line = "";
                while(!"/quit".equals(line)) {
                    // reading from the thread may BLOCK the thread allocated to the connection
                    // (i.e. if there isn't enough data to read)
                    line = reader.readLine();
                    System.out.println("~ " + line);

                    // Writing to a socket can block if the TCP buffer is full.
                    writer.write(line + "\n");
                    writer.flush();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        };
    }
}
