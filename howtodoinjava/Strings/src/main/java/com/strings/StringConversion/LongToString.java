package com.strings.StringConversion;

public class LongToString {

    public static void main(String[] args) {

        // String.valueOf(long l)
        long one = 1;
        Long oneWrap = 1L;
        String oneStr = String.valueOf(one);
        String oneWrapStr = String.valueOf(oneWrap);

        // Long.toString(long l);
        long two = 2;
        Long twoWrap = 2L;
        String twoStr = Long.toString(two);
        String twoWrapStr = Long.toString(twoWrap);

        // call toString() directly against an  Long object
        // Integer year = 2019 <-- you can do this directly, but then most linters bitch that Long  should be written as primitive.
        Long number = 1_000_000_000L;
        String yearString = number.toString();
    }
}
