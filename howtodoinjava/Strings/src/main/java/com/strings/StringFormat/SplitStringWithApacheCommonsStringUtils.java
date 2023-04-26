package com.strings.StringFormat;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class SplitStringWithApacheCommonsStringUtils {

    /*
        This is kind of a cross between the Tokenizer and String Split.
        - This doesn't use regex, but it returns a String.array.

        - The code is MUCH faster (probably because the separatorChars is easier to handle compared to Regex)
     */
    public static void main(String[] args) {
        String[] tokens = StringUtils.split(getInput(), ", ");
        for (String token : tokens) {
            System.out.println(token);
        }
    }

    private static String getInput() {
        System.out.println("Please Enter A String: ");
        return new Scanner(System.in).nextLine();
    }
}
