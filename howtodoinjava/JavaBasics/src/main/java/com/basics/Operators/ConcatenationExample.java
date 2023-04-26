package com.basics.Operators;

public class ConcatenationExample {

    public static void main(String[] args) {

        // Concatenation of 2 strings
        String str1 = "Hello", str2 = " World";
        String str3 = str1 + str2;
        System.out.println(str3);

        // Concatenation of primitive to string
        int num = 26;
        str1 = "Alphabets";
        str2 = num + str1;
        System.out.println(str2);

        // Concatenation of NULL
        str1 = "I am ";
        str2 = null;
        str3 = str1 + str2;
        System.out.println(str3);
    }
}
