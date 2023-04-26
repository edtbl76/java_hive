package FIleOperations;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileCheckingExamples {

    public static void main(String[] args) {
        List<String> files = Arrays.asList("testFile1", "testFile2", "testFile3", "testFile4","testFile5");

        files.forEach(file -> {

            // Check for existence. (Old School way)
            if (new File(file).exists()) {
                System.out.println(file + " exists");
            } else {
                System.out.println(file + " doesn't exist");
            }

            // Check for existence (NIO new school way)
            /*
                This could also be done w/ else statements, but I chose to do this to demonstrate
                that you can use exists or notExists.
             */
            if (Files.exists(Paths.get(file)))
                System.out.println("NIO says, " + file + " exists");

            if (Files.notExists(Paths.get(file)))
                System.out.println("NIO says, " + file + " not exists");

            if (Files.isReadable(Paths.get(file)))
                System.out.println(file + " is readable");

            if (Files.isWritable(Paths.get(file)))
                System.out.println(file + " is writable");

            if (Files.isExecutable(Paths.get(file)))
                System.out.println(file + " is executable");

        });
    }
}
