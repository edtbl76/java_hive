package iteration;

import utils.Generated;

public class FactorialIteration {


    @Generated
    private FactorialIteration() {
        throw new IllegalStateException("Utility Class, For Study");
    }

    public static int factorialIterative(int number) {

        int result = 1;

        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        return result;
    }
}
