package FIleOperations;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BufferedWriteExample {

    private static String CONTENT = "Hello there pardner!";

    public static void main(String[] args) {
        List<String> files = Arrays.asList("testFile1", "testFile2", "testFile3", "testFile4", "testFile5");

        files.forEach(file -> {
            if(new File(file).exists()) {
                System.out.println("Writing to " + file);

                try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                    bw.write(CONTENT);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println(file + " doesn't exist");
            }
        });
    }
}
