package com.strings.Methods;

public class containsExample {

    /*
        boolean contains(CharSequence s)
            - returns true if THIS String contains given CharSequence
     */
    public static void main(String[] args) {

        String badWordSentence = "Yippee Kay-Ay Mother Fucker!";

        String filtered = (badWordSentence.contains("Fucker")) ? badWordSentence.replace("Fucker", "Theresa") : badWordSentence;
        System.out.println(filtered);
    }
}
