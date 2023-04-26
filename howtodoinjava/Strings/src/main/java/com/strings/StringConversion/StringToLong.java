package com.strings.StringConversion;

public class StringToLong {

    public static void main(String[] args) {

        // Check out String to Int
        // Long.parseLong()
        try {
            long lDec = Long.parseLong("1001");
            long lOct = Long.parseLong("1001", 8);
            long lHex = Long.parseLong("1001", 16);
            System.out.println("Decimal[" + lDec + "] Octal[" + lOct + "] Hex[" + lHex + "]");
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

        // Long.parseLong()
        try {
            long lDec1 = Long.valueOf("1001");
            long lOct1 = Long.valueOf("1001", 8);
            long lHex1 = Long.valueOf("1001", 16);
            System.out.println("Decimal[" + lDec1 + "] Octal[" + lOct1 + "] Hex[" + lHex1 + "]");
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

        // new Long(String) Constructor. This uses unboxing to convert long object to a long primitive.
        // no radix here.
        long lDec2 = new Long("1001");
        System.out.println(lDec2);
    }
}
