package com.strings.Methods;

public class charAtExample {

    public static void main(String[] args) {

        /*
            char charAt(int index)
                - returns char at specified index.
                - index should be in (0, length() -1)
                - throws IndexOutOfBoundsException if index is invalid.
         */

        String capital = "Boston";
        try {
            char c = capital.charAt(5);
        } catch (IndexOutOfBoundsException err) {
            err.printStackTrace();
        }
    }
}
