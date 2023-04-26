package FIleOperations;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class FileCreationExamples {

    public static void main(String[] args) throws IOException  {
        createFileUsingFileClass();
        createFileUsingFileOutputStreamClass();
        createFileInNIO();

    }

    // Oldest method... probably the least preferred
    private static void createFileUsingFileClass() throws IOException {
        File file = new File("testFile1");

        if ((file.createNewFile()))
            System.out.println("File created.");
        else
            System.out.println("File exists");

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Test Data");
        }
    }

    // FileOutputStream Method.. so so
    private static void createFileUsingFileOutputStreamClass() throws IOException {
        String data = "Test Data";

        try (FileOutputStream out = new FileOutputStream("testFile2")) {
            out.write(data.getBytes());
        }
    }

    // RECOMMENDED METHOD NIO - Files.write()
    private static void createFileInNIO() throws IOException {

        String data = "Test Data";
        Files.write(Paths.get("testFile3"), data.getBytes());

        // or
        List<String> lines = Arrays.asList("First Line", "Second Line");
        Files.write(Paths.get("testFile4"),
                lines,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);

    }

}
