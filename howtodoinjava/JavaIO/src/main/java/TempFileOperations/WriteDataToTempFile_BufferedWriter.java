package TempFileOperations;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteDataToTempFile_BufferedWriter {

    public static void main(String[] args) throws IOException{

        File temp = File.createTempFile("tempfile", "");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(temp))){

            // write data to temp file.
            bw.write("This is temp data written to temp file");
            System.out.println("Written to temp file: " + temp.getAbsolutePath());
        }
    }
}
