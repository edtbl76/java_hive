package TempFileOperations;

import java.io.File;
import java.io.IOException;

public class DeleteTempFile_NoNIO {

    public static void main(String[] args) {
        File temp;
        try {
            temp = File.createTempFile("tempfile", ".txt");
            System.out.println("Temp file created: " + temp.getAbsolutePath());
            // immediate delete
            temp.delete();
            System.out.println(temp + " exists: " + temp.exists());

            temp = File.createTempFile("tempfile",".txt");
            System.out.println("Temp file created: " + temp.getAbsolutePath());
            // deletes on runtime eixt
            temp.deleteOnExit();
            System.out.println(temp + " exists: " + temp.exists());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
