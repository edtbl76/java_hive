package TempFileOperations;

import java.io.File;
import java.io.IOException;

public class CreateTempFileRecommended {

    public static void main(String[] args) {
        File temp;
        try {
            temp = File.createTempFile("tempfile", ".txt");
            System.out.println("Temp file created: " + temp.getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
