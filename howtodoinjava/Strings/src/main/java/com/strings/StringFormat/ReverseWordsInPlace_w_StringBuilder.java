package com.strings.StringFormat;

import java.util.Scanner;

public class ReverseWordsInPlace_w_StringBuilder {
    /*
        - Reverse String w/ and w/o recursion took an entire string and reversed it. This ignores a "word"
        - We leveled by tokenizing a string so that we can separate words and swap their position (so that a phrase reverses the order of
            the words.

        - now we are going to Reverse the order of the characters of each word, but keep the string in place within the phrase.

     */
    public static void main(String[] args) {

        // Input
        System.out.println("Gimme a multi-word phrase: ");
        String input = new Scanner(System.in).nextLine();

        // Set up the storage for the final structure
        StringBuilder reverseString = new StringBuilder();

        // Use String.split() to convert our input into a String Array
        String[] words = input.split(" ");

        /*
            Iterate through each word
         */
        for (String word : words) {
            String reverseWord = new StringBuilder(word).reverse().toString();
            reverseString.append(reverseWord + " ");
        }
        System.out.println(reverseString.toString());

    }
}
