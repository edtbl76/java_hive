package StructuringConcurrentApplications_2.CancellationAndShutdown_7.Examples;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ReaderThread extends Thread {

    private final Socket socket;
    private final InputStream inputStream;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = socket.getInputStream();
    }

    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException ignored) {

        } finally {
            super.interrupt();
        }
    }

    @Override
    public void run() {
        try {
            byte[] buffer = new byte[10];
            while (true) {
                int count = inputStream.read(buffer);
                if (count < 0) {
                    break;
                } else if (count > 0) {
                    processBuffer(buffer, count);
                }
            }

        } catch (IOException e) {
            /*
                Allow Thread to exit
             */
        }
    }

    private void processBuffer(byte[] buffer, int count) {
    }
}
