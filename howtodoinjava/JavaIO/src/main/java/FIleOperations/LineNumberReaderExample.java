package FIleOperations;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class LineNumberReaderExample {

    public static void main(String[] args) {
        readFromFile("pom.xml");
    }

    private static void readFromFile(String filename) {

        // Build LNR in try-with-resources block
        try (LineNumberReader lnr = new LineNumberReader(new FileReader(filename))) {

            System.out.println("Line " + lnr.getLineNumber());
            lnr.setLineNumber(5);
            System.out.println("Line " + lnr.getLineNumber());

            // read the rest
            String line = null;
            while ((line = lnr.readLine()) != null)
                System.out.println("Line " + lnr.getLineNumber() + ": " + line);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
