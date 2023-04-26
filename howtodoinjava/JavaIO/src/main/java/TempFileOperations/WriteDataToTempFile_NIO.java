package TempFileOperations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteDataToTempFile_NIO {

    public static void main(String[] args) {

        try {
            final Path path = Files.createTempFile("tempfile", "txt");
            System.out.println("Created Temp file: " + path);

            byte[] buf = "some data".getBytes();
            Files.write(path, buf);

            // delete it on exit
            path.toFile().deleteOnExit();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
