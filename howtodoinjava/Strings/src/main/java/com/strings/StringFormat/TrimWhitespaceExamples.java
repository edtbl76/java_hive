package com.strings.StringFormat;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;
import java.util.StringTokenizer;

public class TrimWhitespaceExamples {

    public static void main(String[] args) {
        System.out.println("Enter a sentence with extra spaces between words: ");
        Scanner in = new Scanner(System.in);
        String cleanMe = in.nextLine();

        // METHOD 1: StringUtils.normalizeSpace;
        // This will catch leading/trailing spaces.
        System.out.println(StringUtils.normalizeSpace(cleanMe));

        // METHOD 2: With a REGEX
        // This will NOT catch leading/trailing spaces.
        String regex = "\\s+";
        System.out.println(cleanMe.replaceAll(regex, " "));

        // METHOD 3: With StringBuffer (NOT RECOMMENDED FOR LARGE STRINGS)
        StringTokenizer st = new StringTokenizer(cleanMe, " ");
        StringBuffer sb = new StringBuffer();
        while (st.hasMoreElements()) {
            sb.append(st.nextElement()).append(" ");
        }
        // Convert the SB back to a string. trim will catch any extra space before and after.
        System.out.println(sb.toString().trim());


    }
}
