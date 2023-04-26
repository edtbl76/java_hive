package com.strings.StringConversion;

import org.apache.commons.lang3.StringUtils;

public class StringUtilsExample {

    public static void main(String[] args) {
        String[] mask = {"m", "a", "s", "k"};

        // no delimiter, so everything gets stuck together
        System.out.println(StringUtils.join(mask));

        // Joining w/ periods and flipping to uppercase
        System.out.println(StringUtils.join(mask, ".").toUpperCase());
    }
}
