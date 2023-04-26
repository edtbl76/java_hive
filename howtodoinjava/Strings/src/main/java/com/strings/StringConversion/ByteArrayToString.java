package com.strings.StringConversion;

import java.nio.charset.Charset;

public class ByteArrayToString {

    /*
        When working with:
            - encryption algorithms
            - binary files
            - various types of transport streams.

        You may get text or data as byte[]

     */
    public static void main(String[] args) {
        String name = "Take a byte out of crime";
        byte[] bytes = name.getBytes();

        String str = new String(bytes);
        String strWithCharset = new String(bytes, Charset.defaultCharset());

        System.out.println(name);                   //Original
        System.out.println(bytes.toString());       // Byte Array
        System.out.println(str);                    // Converted Back
        System.out.println(strWithCharset);         // Converted w/ Charset

    }
}
