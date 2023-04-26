package com.strings.Methods;

import java.io.UnsupportedEncodingException;

public class getBytesExample {

    /*
        byte[] getBytes(String charsetName)
            - converts string into byte array using the specified charset encoding

        byte[] getBytes()
            - converts string into byte array using default charset encoding
     */
    public static void main(String[] args) throws UnsupportedEncodingException {

        String doubleNibble = "Byte";

        System.out.println(doubleNibble);
        System.out.println(doubleNibble.getBytes());
        System.out.println(doubleNibble.getBytes("UTF16"));

    }
}
