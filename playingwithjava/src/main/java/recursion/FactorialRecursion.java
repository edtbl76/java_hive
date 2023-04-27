package recursion;


import utils.Generated;

public class FactorialRecursion {

    @Generated
    private FactorialRecursion() {
        // Hiding from Jacoco
    }

    //  Recursive function
    public static int factorial(int n) {
        // Base case
        if (n == 1) {
            return 1;
        }
        // Recursive case
        else {
            return (n * factorial(n - 1));
        }
    }

}
