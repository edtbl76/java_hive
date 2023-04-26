package Conversions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class StringToInputStream_ByteArrayInputStream {

    public static void main(String[] args) {

        String sample = "I am a String. Make me an InputStream.";
        InputStream stream = new ByteArrayInputStream(sample.getBytes());

        System.out.println("String: [" + sample + "]");
        System.out.println("InputStream: [" + stream + "]");
    }
}
