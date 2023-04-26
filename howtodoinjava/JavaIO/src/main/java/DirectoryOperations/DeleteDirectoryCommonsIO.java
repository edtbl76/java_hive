package DirectoryOperations;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class DeleteDirectoryCommonsIO {
    public static void main(String[] args) {
        File file = FileUtils.getFile("tempIdea");
        try {
            FileUtils.deleteDirectory(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

