package IOvNIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.stream.IntStream;

public class NIOSample {

    public static void main(String[] args) throws IOException {

        try (RandomAccessFile accessFile = new RandomAccessFile("pom.xml", "r");
             FileChannel in = accessFile.getChannel()){

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while(in.read(buffer) > 0 ) {
                buffer.flip();
                IntStream.range(0, buffer.limit()).forEach(value -> System.out.print((char) buffer.get()));
                buffer.clear();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
