package com.strings.StringFormat;

import java.util.Scanner;
import java.util.StringTokenizer;

/*
    TOKENIZER DISCLAIMER
        - this is legacy, retained for compatibility purposes.
        - don't fucking use it.
 */
public class SplitStringsWithTokenizerMultiDelimiter {

    public static void main(String[] args) {

        StringTokenizer multiTokenizer = new StringTokenizer(getInput(), " ,;:");
        while (multiTokenizer.hasMoreTokens()) {
            System.out.println(multiTokenizer.nextToken());
        }
    }

    private static String getInput() {
        System.out.println("Please enter a string with several special type characters: ");
        return new Scanner(System.in).nextLine();
    }
}
