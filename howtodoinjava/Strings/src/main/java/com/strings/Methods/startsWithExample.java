package com.strings.Methods;

public class startsWithExample {

    public static void main(String[] args) {

        /*
            boolean startsWith(String prefix, int offset)
                - checks whether this String contains the specified prefix or not, starting from the specified index.

            boolean startsWith(String prefix)
                - checks whether this String contains the specified prefix or not, starting from 0
         */

        String compound = "doorknob";

        System.out.println(compound.startsWith("door"));    // true (starts from 0)
        System.out.println(compound.startsWith("knob", 4));     // also true (starts from 4
    }
}
