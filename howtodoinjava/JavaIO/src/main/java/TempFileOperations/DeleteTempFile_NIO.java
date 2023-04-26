package TempFileOperations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DeleteTempFile_NIO {

    public static void main(String[] args) {

        try {
            final Path path = Files.createTempFile("tempfile", "");
            System.out.println("Tempfile " + path + " created");

            // Delete on exit
            Files.deleteIfExists(path);

            // Immediate Delete
            Files.delete(path);


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
