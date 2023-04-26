package com.strings.Methods;

public class codePointAtExample {

    /*
        int codePointAt(int index)
            - returns unicode code point value of the specified index rather than the character itself.
     */
    public static void main(String[] args) {

        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        // lower
        for(int ctr = 0; ctr < alphabet.length(); ctr++) {
            System.out.print("Character: " + alphabet.charAt(ctr));
            System.out.println("\tCodepoint: " + alphabet.codePointAt(ctr));
        }

        // upper
        for(int ctr = 0; ctr < alphabet.length(); ctr++) {
            System.out.print("Character: " + alphabet.toUpperCase().charAt(ctr));
            System.out.println("\tCodepoint: " + alphabet.toUpperCase().codePointAt(ctr));
        }

    }
}
