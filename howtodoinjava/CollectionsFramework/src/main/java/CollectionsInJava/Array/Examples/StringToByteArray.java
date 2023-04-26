package CollectionsInJava.Array.Examples;


import java.util.Arrays;
import java.util.Base64;

public class StringToByteArray {

    public static void main(String[] args) {

        String string = "I am a string";
        System.out.println("Original: " + string);

        // String Get Bytes
        byte[] bytes = string.getBytes();
        System.out.println(bytes);


    }

}
