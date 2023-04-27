package multithreading.threads.callables;

import utils.Generated;

import java.util.concurrent.Callable;

@Generated
@SuppressWarnings("all")
public class DemoSumTask4 {

    final int number = 10;

    /*
        Further refactoring from 3
        - I can extract the method (summation) which is likely to be reusable.
        This creates a nice simple terse line of code.
     */
    Callable<Integer> sumTask = () -> summation(number);

    private Integer summation(int max) {
        int sum = 0;
        for (int i = 0; i <= max; i++) {
            sum += i;
        }
        return sum;
    }

}
