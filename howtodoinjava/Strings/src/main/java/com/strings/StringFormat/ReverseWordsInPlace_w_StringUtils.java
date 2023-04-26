package com.strings.StringFormat;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class ReverseWordsInPlace_w_StringUtils {

    /*
        Check out the StringBuilder version first. It's cool.

        However, Apache Commons tends to be so much easier.
     */

    public static void main(String[] args) {

        // Input
        System.out.println("Please Enter Multiple Words: ");
        String input = new Scanner(System.in).nextLine();

        // Let's do this in phases so you can see how we get to the final result.
        String reversedString = StringUtils.reverse(input);
        String reverseWords = StringUtils.reverseDelimited(reversedString, ' ');
        String allInOne = StringUtils.reverseDelimited(StringUtils.reverse(input), ' ');

        // Original String
        System.out.println(input);

        // This is swaps the entire strings order (char by  char)
        System.out.println(reversedString);

        // This  uses a delimiter to reorder the words (which  returns them to their original order).
        System.out.println(reverseWords);

        // This is just  for posterity
        System.out.println(allInOne);

    }
}
