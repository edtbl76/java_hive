package com.strings.Methods;

public class equalExample {

    public static void main(String[] args) {

        /*
            boolean equal(Object obj)
                - Compares string w/ specified string and returns true if they match

            boolean equalsIgnoreCase(String string)
                - same as above, but in a case insensitive way.
         */

        String name = "Connor";

        // Case Sensitive
        if (name.equals("Connor")) {
            System.out.println("True - Case Sensitive");
        } else {
            System.out.println("False");
        }

        // Case Insensitive
        if (name.equalsIgnoreCase("CONNOR")) {
            System.out.println("True - Case Insensitive");
        } else {
            System.out.println("False");
        }



    }
}
