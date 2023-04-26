package FIleOperations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BufferedReadExample {

    public static void main(String[] args) {

        List<String> files = Arrays.asList("testFile1", "testFile2", "testFile3", "testFile4");

        files.forEach(file -> {
            System.out.println("Reading: " + file);
            if (new File(file).exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while((line = br.readLine()) != null)
                        System.out.println(line);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
