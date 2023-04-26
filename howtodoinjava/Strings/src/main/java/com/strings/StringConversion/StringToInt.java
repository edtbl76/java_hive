package com.strings.StringConversion;

public class StringToInt {

    public static void main(String[] args) {


        // Integer.parseInt RETURNS PRIMITIVE int +++++++++++++++++++++
        /*
            PARSEINT = returns a PRIMITIVE

            public static int parseInt(String s)  throws NumberFormatException {}
            public static int parseInt(string s, int radix) throws NumberFormatException {}

                - radix is numeral system base
                - NFException occurs if string is null/empty or the value isn't a parsable int in base 10.
         */
        try {
            int intDec = Integer.parseInt("1001");
            int intOct = Integer.parseInt("1001", 8);
            int intHex = Integer.parseInt("1001", 16);
            System.out.println("Decimal[" + intDec + "] Octal[" + intOct + "] Hex[" + intHex + "]");
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }


        // Integer.valueOf RETURNS REFERENCE OBJECT TYPE WRAPPER +++++++++++++++++++++
        /*
            RETURN VALUE is INTEGER TYPE WRAPPER

            public static Integer valueOf(String s) throws NumberFormatException {}
            public static Integer (String s, int radix) throws NumberFormatException {}
         */
        try {
            Integer intDec1 = Integer.valueOf("1001");
            Integer intOct1 = Integer.valueOf("1001", 8);
            Integer intHex1 = Integer.valueOf("1001", 16);
            System.out.println("Decimal[" + intDec1 + "] Octal[" + intOct1 + "] Hex[" + intHex1 + "]");
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

        // Integer.decode() ----------------- only for DECIMAL, HEX and OCTAL
        /*
                public static Integer decode(string nm) throws NFE
         */
        try {
            Integer intDec2 = Integer.decode("+100");   // decimals start w/ +/-
            Integer intOct2 = Integer.decode("+0100");  // octals start w/ +0/-0
            Integer intHex2 = Integer.decode("+0x100"); // hex starts w/ +0x/-0x
            System.out.println("Decimal[" + intDec2 + "] Octal[" + intOct2 + "] Hex[" + intHex2 + "]");
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }
}
