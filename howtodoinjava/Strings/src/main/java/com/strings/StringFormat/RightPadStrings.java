package com.strings.StringFormat;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class RightPadStrings {

    // Check Out LeftPad strings for the details on StringUtils (It's the same for rightPad)
    public static void main(String[] args) {

        String str = getInput();
        // We have to add a character at the  end so we can demonstrate the padding.
        System.out.println(StringUtils.rightPad(str, 20, " ") + "#");
        System.out.println(StringUtils.rightPad(str, 30, " ") + "#");
        System.out.println(StringUtils.rightPad(str, 15, " ") + "#");
        // If the length is shorter than the string, there is no padding.
        System.out.println(StringUtils.rightPad(str, 1, " ") + "#");

        // ZERO PADDING EXAMPLE
        System.out.println(StringUtils.rightPad("0123456789", 10, "0"));
        System.out.println(StringUtils.rightPad("789", 10, "0"));
        System.out.println(StringUtils.rightPad("56789", 10, "0"));
    }

    private static String getInput() {
        System.out.println("Enter a string: ");
        return new Scanner(System.in).nextLine();
    }
}
