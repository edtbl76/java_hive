package IOvNIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IOSample {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(
                new FileReader("pom.xml")
        )) {
            String currentLine;
            while((currentLine = br.readLine()) != null) {
                System.out.println(currentLine);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
