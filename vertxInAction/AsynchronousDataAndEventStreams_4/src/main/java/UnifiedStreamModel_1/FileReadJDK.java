package UnifiedStreamModel_1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileReadJDK {

    public static void main(String[] args) {
        // create a file to read and a buffer to stream the file in chunks.
        File file = new File ("pom.xml");
        byte[] buffer = new byte[1024];

        // try-with-resources syntax guarantees reader.close() is called.
        try (FileInputStream in = new FileInputStream(file)) {
            // set the first chunk
            int count  = in.read(buffer);
            while (count != -1) {
                // while there is a chunk, print a String from the buffer. Offset is the start point, and count is the length.
                System.out.println(new String(buffer, 0, count));
                count = in.read(buffer);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("\n--- DONE");
        }
    }
}
