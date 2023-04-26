package DirectoryOperations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class DirCopyExample {

    public static void main(String[] args) throws IOException  {

        // Source directory
        File src = new File(".idea");
        File dst = new File("tempIdea");
        copyFolder(src, dst);
    }

    private static void copyFolder(File sourceDir, File destinationDir) throws IOException {

        if (sourceDir.isDirectory()) {
            // if dest not exist, make it.
            if (!destinationDir.exists()) {
                destinationDir.mkdir();
                System.out.println("Created Directory: " + destinationDir);
            }

            // get files from src dir and copy them to dest
            String[] files = sourceDir.list();
            for (String file : files) {
                copyFolder(new File(sourceDir, file), new File(destinationDir, file));
            }
        } else {
            Files.copy(sourceDir.toPath(), destinationDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied: " + destinationDir);
        }
    }
}
