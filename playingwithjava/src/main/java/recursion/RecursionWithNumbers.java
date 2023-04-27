package recursion;

public class RecursionWithNumbers {

    private RecursionWithNumbers() {}

    public static int sumAll(int number) {

        // base
        if (number == 0) {
            return 0;
        }

        return number + sumAll(number - 1);
    }

    public static int fibonacci(int number) {

        if (number <= 1) {
            return number;
        }

        return fibonacci(number - 1) + fibonacci(number - 2);
    }

    public static int greatestCommonDivisor(int number1, int number2) {

        // base case
        if (number1 == number2) {
            return number1;
        }

        // Recursive case
        if (number1 > number2) {
            return greatestCommonDivisor(number1 - number2, number2);
        } else {
            return greatestCommonDivisor(number1, number2 - number1);
        }
    }

    @SuppressWarnings("java:S125")
    public static boolean checkPowerOf(int base, int exponent) {

            // base case
            if (base <= 1) {
                return base == 1;
            }
                /*
                    simplified if statement
                        - as long as the first part is true, we keep recursing.
                        - after the &&. we recurse until it is either false, or we get to 1

                     NOTE:
                     - This makes 100% code coverage impossible.
                     - this is a short-circuiting logic operator.
                     - There are 4 possible test branches:
                        True True - can be evaluated
                        True False - can be evaluated
                        False x    - the right side of the equation is
                        never evaluated, nor does it matter .

                    Alternative Code:
                    if (base % exponent == 0) {
                        checkPowerOf(base / exponent, exponent)
                    } else {
                        return false
                    }

                 */
                boolean condition = base % exponent == 0;
                boolean recursion = checkPowerOf(base / exponent, exponent);
            return condition && recursion;

    }
}
