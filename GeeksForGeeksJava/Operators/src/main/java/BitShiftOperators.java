public class BitShiftOperators {

    public static void main(String[] args) {

        /*
            Start w/ right shifts.
            - simple shift that works it's way down from 255
         */
        int num1 = 255;
        System.out.println("Number1                      : " + num1);
        for (int i = 1; i < 8; i++) {
            System.out.println("Shift Right " + i + "                : " + (num1 >> i));
        }
        System.out.println("\n");

        /*
            HERE, I use the unary NOT operator to swap to the one's complement.
            - Then I loop through and demonstrate the signed and unsigned shifts.
            - SIGNED shift preserves the sign for one's/two's complement.
            - UNSIGNED ignores the sign.
         */
        System.out.println("Complement of Number1        : " + ~num1);
        for (int i = 1; i < 8; i++) {
            System.out.println("Shift Right " + i + " (Signed >>)    : " + (~num1 >> i));
            System.out.println("Shift Right " + i + " (UnSigned >>>) : " + (~num1 >>> i));
        }

        /*
            Same thing w/ left shift.

            NOTE: There is no unsigned left shift. <<<
         */
        int num2 = 2;
        System.out.println("Number2                      : " + num2);
        for (int i = 1; i < 8; i++) {
            System.out.println("Shift Left " + i + "                : " + (num2 << i));
        }
        System.out.println("\n");

        System.out.println("Complement of Number2        : " + ~num2);
        for (int i = 1; i < 8; i++) {
            System.out.println("Shift Left " + i + "                : " + (~num2 << i));
        }
    }
}
