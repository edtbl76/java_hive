package com.strings.StringFormat;

import com.google.common.base.Splitter;

import java.util.Scanner;
import java.util.regex.Pattern;

public class SplitStringsWithGoogleGuava_w_PatternMatch {

    /*
        FUCKIN' GOOGLE.
        - Guava Splitter is HIGHLY regarded.
        - Its easy  to read, and it works very well.
     */
    public static void main(String[] args) {
        Splitter splitter = Splitter.on(Pattern.compile("[\\s+\\W]+")).omitEmptyStrings().trimResults();

        Iterable<String> tokens = splitter.split(getInput());
        for (String token : tokens)
            System.out.println(token);
    }

    private static String getInput() {
        System.out.println("Please Enter a String: ");
        return new Scanner(System.in).nextLine();

    }
}
