public class BitwiseOperators {

    public static void main(String[] args) {

        // Initialize my variables
        int num1 = 7;  // 0111
        int num2 = 5;  // 0101

        // Display Numbers for posterity
        System.out.println("Number1 - Decimal: " + num1 + " Binary: " + Integer.toBinaryString(num1));
        System.out.println("Number2 - Decimal: " + num2 + " Binary: " + Integer.toBinaryString(num2));

        /*
            BITWISE AND

                    0111
                    0101
                    ----
                    0101    = 5
         */
        int bitwiseAnd = num1 & num2;
        System.out.println(num1 + " & " + num2 + " = [" + bitwiseAnd + ", " + Integer.toBinaryString(bitwiseAnd) + "]");


        /*
            BITWISE OR

                    0111
                    0101
                    ----
                    0111    = 7
         */
        int bitwiseOr = num1 | num2;
        System.out.println(num1 + " | " + num2 + " = [" + bitwiseOr+ ", " + Integer.toBinaryString(bitwiseOr) + "]");

        /*
            BITWISE XOR

                    0111
                    0101
                    ----
                    0010    = 2
         */
        int bitwiseXor = num1 ^ num2;
        System.out.println(num1 + " ^ " + num2 + " = [" + bitwiseXor+ ", " + Integer.toBinaryString(bitwiseXor) + "]");


        int bitwiseComplement1 = ~num1;
        int bitwiseComplement2 = ~num2;
        System.out.println("Bitwise Complement of " + num1 +
                " = [" + bitwiseComplement1 + ", " + Integer.toBinaryString(bitwiseComplement1) + "]");
        System.out.println("Bitwise Complement of " + num2 +
                " = [" + bitwiseComplement2 + ", " + Integer.toBinaryString(bitwiseComplement2) + "]");

         System.out.println(~1 + " " + Integer.toBinaryString(~1));
    }
}
