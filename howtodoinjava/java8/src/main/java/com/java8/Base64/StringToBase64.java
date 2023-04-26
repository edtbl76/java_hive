package com.java8.Base64;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class StringToBase64 {

    public static void main(String[] args) {
        Base64.Encoder encoder = Base64.getEncoder();
        String normalString= "username:password";

        String encodedString = encoder.encodeToString(normalString.getBytes(StandardCharsets.UTF_8));
        System.out.println(encodedString);
    }
}
