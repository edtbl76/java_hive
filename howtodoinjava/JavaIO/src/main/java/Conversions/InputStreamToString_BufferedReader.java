package Conversions;

import java.io.*;

public class InputStreamToString_BufferedReader {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        /*
            This is the easiest and most popular way.. despite it being OOOOOLD
         */

        InputStream in = new FileInputStream("pom.xml");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                out.append(line);
            }
            System.out.println(out.toString());
        }
    }
}
