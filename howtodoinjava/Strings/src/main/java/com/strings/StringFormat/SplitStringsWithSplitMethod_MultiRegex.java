package com.strings.StringFormat;

import java.util.Scanner;

public class SplitStringsWithSplitMethod_MultiRegex {

    /*
        THIS really demonstrates the power and flexibility of later Java versions.
        - instead of "doing everything itself" it focused on leveraging well scrutinezed patterns.

        The tokenizer had to parse a string for creating tokens.
        - String Split improves upon this by implementing regex, which is MUCH more powerful.

        By using split(), we are also manipulating an existing structure instead of passing our String to a newly
        dynamically allocated structure (bloating the call stack, slowing down our work w/ extra processing etc.)
     */
    public static void main(String[] args) {

        String[] tokens = getInput().split("[\\s+\\W+]");
        for (String token : tokens) {
            System.out.println(token);
        }

    }

    private static String getInput() {
        System.out.println("Enter a string: ");
        return new Scanner(System.in).nextLine();
    }

}
