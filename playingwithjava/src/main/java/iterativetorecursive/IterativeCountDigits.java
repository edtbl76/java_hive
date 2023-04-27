package iterativetorecursive;

import utils.Generated;

public class IterativeCountDigits {

    @Generated
    public IterativeCountDigits() {
        // hide from coverage
    }

    public static int countDigits(int number) {
        int count = 0;
        while (number > 0) {
            number /= 10;
            count++;
        }
        return count;
    }

    @Generated
    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        int input = 1435043;
        int numberOfDigits = countDigits(input);
        System.out.println("Number of digits in " + input + " = " + numberOfDigits);
    }
}
