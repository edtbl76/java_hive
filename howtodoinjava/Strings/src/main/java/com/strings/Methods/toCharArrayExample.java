package com.strings.Methods;

public class toCharArrayExample {

    /*
        char[] toCharArray()
            - converts string to a char array
     */
    public static void main(String[] args) {

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        System.out.println(alphabet);

        for (char c : alphabet.toCharArray()) {
            System.out.println(c);
        }

    }
}
