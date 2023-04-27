package multithreading.threads.callables;

import utils.Generated;

import java.util.concurrent.Callable;

@Generated
@SuppressWarnings("all")
public class DemoSumTask3 {

    final int number = 10;

    /*
        Variation on Example 2.
        - Lambdas provide a more terse means of representing anonymous classes.
     */
    Callable<Integer> sumTask = () -> {
        int sum = 0;
        for (int i = 0; i <= number; i++) {
            sum += i;
        }
        return sum;
    };


}
