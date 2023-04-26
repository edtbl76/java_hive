package com.strings.StringFormat;

import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class ReverseStringByWords {

    public static void main(String[] args) {


        // handle input
        System.out.println("Please enter a phrase with multiple words: ");
        String input = new Scanner(System.in).nextLine();

        // build my storage
        StringBuilder reverseString = new StringBuilder();
        Stack<String> myStack = new Stack<>();

        // Let's get to work

        // METHOD 1: Tokenizer
        // Tokenizer is essentially creating a space separated structure here.
        StringTokenizer tokenizer = new StringTokenizer(input, " ");

        // This transfers each "token" from the string onto the stack.
        while (tokenizer.hasMoreTokens()) {
            myStack.push(tokenizer.nextToken());
        }

        // now we pull off the pancakes and reverse our order into the SBuilder, restoring the spaces.
        while (!myStack.empty()) {
            reverseString.append(myStack.pop() + " ");
        }

        System.out.println(reverseString.toString());   // don't forget the SB -> String conversion


    }
}
