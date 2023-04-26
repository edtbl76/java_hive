package com.strings.Methods;

public class trimExample {

    /*
        String trim()
            - removes leading/trailing white spaces from Java string
     */
    public static void main(String[] args) {

        // print orig as a demonstration
        String extracted = "\n\t\t\nextracted   ";
        System.out.println("Original: [" + extracted + "]");

        // trimmed.
        System.out.println("Trimmed: [" + extracted.trim() + "]");
    }
}
