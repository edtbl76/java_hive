package com.strings.NIOReadFiles;

import java.io.IOException;
import java.io.RandomAccessFile;
// NOTE: We are now in the NIO world!
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Four_FasterCopyWithMappedByteBuffer {

    public static void main(String[] args) {

        try {
            // Start the same;
            RandomAccessFile file = new RandomAccessFile("filetest", "r");
            FileChannel fc = file.getChannel();

            /*
                This is a type of ByteBuffer, but the contents are a MEMORY MAPPED region of a file.
                - these are created by FileChannel.map() method (which is what performs the file memory mapping )
                -
             */
            MappedByteBuffer buffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());

            // loads contents of buffer into physical memory and then prints out the data 1 chunk at a time.
            buffer.load();
            for (int i = 0; i < buffer.limit(); i++) {
                System.out.print((char) buffer.get());
            }
            buffer.clear();     // this is actually derived all the way from Buffer class.
                                // this sets position to 0 and preps it either to be read from again, or to be closed.
            fc.close();
            file.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
