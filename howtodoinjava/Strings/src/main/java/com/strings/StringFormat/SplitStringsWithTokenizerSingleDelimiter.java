package com.strings.StringFormat;

import java.util.Scanner;
import java.util.StringTokenizer;

/*
    TOKENIZER DISCLAIMER
        - this is legacy, retained for compatibility purposes.
        - don't fucking use it.
 */
public class SplitStringsWithTokenizerSingleDelimiter {

    public static void main(String[] args) {

        System.out.println("Please input a string: ");
        String in = new Scanner(System.in).nextLine();

        // METHOD 1: Single Delimiter
        /*
            The default delimiter is a space.
            - The tokenizer is a kind of generator, so you can only iterate through it once. (The tokens
            are removed as they are consumed).
            - this means that countTokens() reduces as you iterate through it.
         */
        StringTokenizer tokenizer = new StringTokenizer(in);

        System.out.println("No. of Tokens: " + tokenizer.countTokens());
        while (tokenizer.hasMoreTokens()) {
            System.out.println(tokenizer.nextToken());
        }
        System.out.println("Remaining Tokens: " + tokenizer.countTokens());

    }
}
