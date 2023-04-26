package com.strings.NIOReadFiles;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Two_ReadSmallFileInBufferOfFileSize {

    public static void main(String[] args) {

        try {
            // old school random access file, create a file channel and get the size of the file.
            RandomAccessFile file = new RandomAccessFile("./testfile", "r");
            FileChannel fc = file.getChannel();
            long fileSize = fc.size();

            // MANUALLY build the buffer of the correct size, and read the file into it
            ByteBuffer buffer = ByteBuffer.allocate((int) fileSize);
            fc.read(buffer);

            // flip the direction of the buffer ((i.e. it was reading data from the file channel, now we're writing it out to System.out)
            // ONE CHAR AT A TIME!!!!
            buffer.flip();
            for (int i = 0; i < fileSize; i++) {
                System.out.print((char) buffer.get());
            }

            // close that shit up.
            fc.close();
            file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
