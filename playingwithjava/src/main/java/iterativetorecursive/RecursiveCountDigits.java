package iterativetorecursive;

import utils.Generated;

public class RecursiveCountDigits {

    @Generated
    public RecursiveCountDigits() {
        // hiding from code coverage
    }

    public static int countDigits(int number) {

        // Base
        if (number <= 1) {
            return 1;
        }
        // Recursive
        else {
            return 1 + countDigits(number / 10);
        }

    }

    @Generated
    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        int input = 1435043;
        int numberOfDigits = countDigits(input);
        System.out.println("Number of digits in " + input + " = " + numberOfDigits);
    }
}
