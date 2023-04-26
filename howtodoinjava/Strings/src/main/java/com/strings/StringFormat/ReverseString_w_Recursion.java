package com.strings.StringFormat;

import java.util.Scanner;

public class ReverseString_w_Recursion {

    public static void main(String[] args) {

        System.out.println("Enter a string of characters.");
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        String reverseString = reverseString(input);

        System.out.println(reverseString);

    }

    private static String reverseString(String inString) {

        if (inString.isEmpty())
            return inString;

        /*
            inString.substring(1) is a substring that represents everything but the first character.
            inString.charAt(0) is that first character (so we are storing it in each call level of the recursion.

            when we hit this assignment
                - we are chopping off the first character and technically "storing it" in the active recursion call()
                - we pass everything but that first character to the next level. (So each level is a string 2 char shorter)

            eventually the recursion ends, and inString is empty. which returns the empty string back up as we unwind() the
            recursion stack.

            This is clever, because we are using the recursion call stack as our storage for the reversal.
            Stack = LIFO, (which reverses order)

            As we peel our way out we now focus on the assignment statements and return statements.
                - the assignment result = <result of previous level> + what i have stored now.

         */
        String result = reverseString(inString.substring(1)) + inString.charAt(0);
        return result;

    }
}

/*
    RECURSION TREE

        L1D     Hello   substring = ello    char = H    DOWN
        L2D     ello    substring = llo     char = e
        L3D     llo     substring = lo      char = l
        L4D     lo      substring = l       char = l
        L5D     o       substring =         char = o
        L6BTTM  empty   return                          BOTTOM
        L5U             "" + o      return o
        L4U             o + l       return ol
        L3U             ol + l      return oll
        L2U             oll + e     return olle
        L1U             olle + H    return olleH

 */
