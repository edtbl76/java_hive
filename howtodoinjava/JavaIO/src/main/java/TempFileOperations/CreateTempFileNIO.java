package TempFileOperations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreateTempFileNIO {

    public static void main(String[] args) {

        try {
            final Path path = Files.createTempFile("tempfile", ".txt");
            System.out.println("Temp file: " + path);

            path.toFile().deleteOnExit();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
