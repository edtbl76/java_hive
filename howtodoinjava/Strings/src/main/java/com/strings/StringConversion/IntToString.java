package com.strings.StringConversion;

public class IntToString {

    public static void main(String[] args) {

        // String.valueOf(int i)        (works for int or Integer)
        int one = 1;
        Integer oneWrap = 1;
        String oneStr = String.valueOf(one);
        String oneWrapStr = String.valueOf(oneWrap);

        // Integer.toString(int i);
        int two = 2;
        int twoWrap = 2;
        String twoStr = Integer.toString(two);
        String twoWrapStr = Integer.toString(twoWrap);

        // call toString() directly against an Integer object
        // Integer year = 2019 <-- you can do this directly, but then most linters bitch that Integer should be written as primiitive int.
        Integer year = new Integer(2019);
        String yearString = year.toString();

    }
}
