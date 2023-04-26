package com.strings.NIOReadFiles;

import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Three_ReadingInLargeChunks {

    public static void main(String[] args) {

        try {
            // Starts out the same as EX 2, but we don't get the entire file size.
            RandomAccessFile file = new RandomAccessFile("./filetest", "r");
            FileChannel fc = file.getChannel();

            // Set a "chunk" size
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            // now we just keep reading from the buffer until its completely drained
            while (fc.read(buffer) > 0) {

                // switch  IO context from reading from buffer to writing
                buffer.flip();
                // prints 1 chunk at a time
                for (int i = 0; i < buffer.limit(); i++) {
                    System.out.print((char) buffer.get());
                }
                buffer.clear(); // clears/compacts the data;
            }

            // close it up
            fc.close();
            file.close();

        } catch (IOException iox) {
            iox.printStackTrace();
        }
    }
}
