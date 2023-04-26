package com.basics.DataTypes;

public class MultipleReferencesToSameObject {

    public static void main(String[] args) {
        String str1, str2;

        // Assigns reference of String object to str1
        str1 = new String("Hello World!!");

        // Assigns reference stored in str1 to str2
        str2 = str1;

        // Print 'em out.
        System.out.println(str1);
        System.out.println(str2);
    }
}
