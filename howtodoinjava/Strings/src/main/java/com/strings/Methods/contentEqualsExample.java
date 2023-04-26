package com.strings.Methods;

public class contentEqualsExample {

    /*
        boolean contentEquals(StringBuffer sb)
            - compares THIS string to the specified StringBuffer

            (Also supports an  overloaded version w/ a Char Sequence)
     */
    public static void main(String[] args) {

        StringBuffer sb = new StringBuffer("I am synchronized, and thread safe");

        String result = ("synchronized".contentEquals(sb)) ? "Equal" : "Not Equal";
        System.out.println(result);
    }
}
