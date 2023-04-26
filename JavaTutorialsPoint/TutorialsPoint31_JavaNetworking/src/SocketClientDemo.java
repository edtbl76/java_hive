import java.net.*;
import java.io.*;

public class SocketClientDemo {

    public static void main(String[] args) {
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);

        try {
            System.out.println("Connecting to " + serverName + ":" + port);
            Socket client = new Socket(serverName, port);

            System.out.println("Connected to " + client.getRemoteSocketAddress());

            OutputStream outputStream = client.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            dataOutputStream.writeUTF("Hello from " + client.getLocalSocketAddress());
            InputStream inputStream = client.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            System.out.println("Server says " + dataInputStream.readUTF());
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
