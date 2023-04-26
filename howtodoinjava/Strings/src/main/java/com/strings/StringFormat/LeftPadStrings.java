package com.strings.StringFormat;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class LeftPadStrings {

    public static void main(String[] args) {

        /*
            StringUtils (From Apache Commons) For LeftPadding.

                public static string leftPad(final String str)
                public static string leftPad(final String str, final int size)
                public static string leftPad(final String str, final int size, final char padChar)

                    - str is the string you want to pad (null OK)
                    - size to pad (i.e. total resulting length of string)
                    - pad fill character

                        - NOTE: only pads if the size of the string is larger.

         */
        String str = getInput();;
        System.out.println(StringUtils.leftPad(str, 20, " "));
        System.out.println(StringUtils.leftPad(str, 30, " "));
        System.out.println(StringUtils.leftPad(str, 15, " "));

        // NOTE: If the size is less than the original string, you get the original string.
        System.out.println(StringUtils.leftPad(str, 1, " "));

        // ZERO PADDING EXAMPLE
        System.out.println(StringUtils.leftPad("0123456789", 10, "0"));
        System.out.println(StringUtils.leftPad("789", 10, "0"));
        System.out.println(StringUtils.leftPad("56789", 10, "0"));

    }

    private static String getInput() {
        System.out.println("Enter a string");
        return new Scanner(System.in).nextLine();
    }
}
