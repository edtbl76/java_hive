package Conversions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class InputStreamToString_Scanner {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        try (FileInputStream fis = new FileInputStream("pom.xml");
             Scanner in = new Scanner(fis, StandardCharsets.UTF_8).useDelimiter("\n")) {
            // This is cool. I have a ternary operator as the argument to my print statement :)
            System.out.println(in.hasNext() ? in.next() : "");
        }
    }
}
