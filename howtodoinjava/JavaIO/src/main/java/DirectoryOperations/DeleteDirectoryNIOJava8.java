package DirectoryOperations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class DeleteDirectoryNIOJava8 {

    /*
    OH THIS IS SOOOOOOOOO MUCH BETTER.
        You can combine Java Streams w/ NIO....
     */
    public static void main(String[] args) {
        Path dir = Paths.get("tempIdea");
        try {
            Files.walk(dir)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(file -> {
                        System.out.println("Deleting: " + file);
                        file.delete();
                    });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
