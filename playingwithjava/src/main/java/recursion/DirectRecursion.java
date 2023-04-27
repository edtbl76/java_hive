package recursion;

import utils.Generated;

public class DirectRecursion {

    @Generated
    // Suppress Warning so I can hide from coverage
    @SuppressWarnings("java:S1118")
    public DirectRecursion() {
        // hiding class from coverage
    }

    /*
        one-step recursive call: the method makes a call inside its own body
     */
    public static int square(int n) {

        // Base Case
        if (n == 0) {
            return 0;
        }
        // Recursive Case
        else {
            return square(n - 1) + (2 * n) - 1;
        }
    }

}
