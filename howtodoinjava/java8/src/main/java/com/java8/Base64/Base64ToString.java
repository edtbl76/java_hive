package com.java8.Base64;

import java.util.Base64;

public class Base64ToString {
    public static void main(String[] args) {
        String encodedString = "dXNlcm5hbWU6cGFzc3dvcmQ=";
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedByteArray = decoder.decode(encodedString);

        // Verify the decoded  string
        System.out.println(decodedByteArray);
    }
}
