package com.strings.Methods;

public class copyValueOfExample {

    /*
        THIS IS FUCKING DEPRECATED
        static String copyValueOf(char[] data)
            - returns a string that contains the characters of the specified character array

        static String copyValueOf(char[] data, int offset, int count)
            - same as above, but it selects characters from the provided array based on offset (i.e. where to start)
            and count (how many characters you want)

        NOTE:  there are examples of valueOf in here as well, because it does the same friggin thing.
     */
    public static void main(String[] args) {

        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        /*
            Dumbest piece of code ever.
            // the indentiation  is to  outline what it is  doing.

            1.) convert the original string to a char[].
            2.) convert the char[] back to a string via  copyValueOf()
            3.) make it upper case.

            The first two steps contradict each other...:)
         */
        System.out.println(
                String.copyValueOf(
                        alphabet.toCharArray()
                ).toUpperCase());

        // this does the same thing.
        System.out.println(
                String.valueOf(
                        alphabet.toCharArray()
                ).toUpperCase());


        /*
            Example of the second one, just snagging a random offset and count.
         */
        System.out.println(
                String.copyValueOf(
                        alphabet.toCharArray(), 10, 9
                ));

        System.out.println(
                String.valueOf(
                        alphabet.toCharArray(), 10, 9
                ));

    }
}
