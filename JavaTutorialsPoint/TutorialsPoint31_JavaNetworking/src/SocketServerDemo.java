import java.net.*;
import java.io.*;

public class SocketServerDemo extends Thread {

    private ServerSocket serverSocket;

    private SocketServerDemo(int port)
        throws IOException
    {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
    }

    public void run() {
        while(true) {
            try {
                System.out.println("Waiting for client on :" + serverSocket.getLocalPort() + "...");

                Socket server = serverSocket.accept();

                System.out.println("Just connected to " + server.getRemoteSocketAddress());
                DataInputStream dataInputStream = new DataInputStream(server.getInputStream());

                System.out.println(dataInputStream.readUTF());
                DataOutputStream dataOutputStream = new DataOutputStream(server.getOutputStream());
                dataOutputStream.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
                    + "\nGoodbye!" );
                server.close();

            } catch (SocketTimeoutException ste) {
                System.out.println("Socket Timed Out.");
                ste.printStackTrace();
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);

        try {
            Thread t = new SocketServerDemo(port);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
