package CollectionsInJava.Array.Examples;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;

public class ByteArrayToString {

    public static void main(String[] args) {

        String name = "Ed Mangini";
        System.out.println("Original String: " + name);

        // Convert to bytes
        byte[] bytes = name.getBytes();


        // ByteArray To String
        System.out.println(new String(bytes));

        // Bytearray -> String w/ Charset.
        System.out.println(new String(bytes, Charset.defaultCharset()));

        // Base64
        String encoded = Base64.getEncoder().encodeToString(bytes);
        byte[] decoded = Base64.getDecoder().decode(encoded);
        System.out.println(encoded + " -> " + new String(decoded));

    }
}
