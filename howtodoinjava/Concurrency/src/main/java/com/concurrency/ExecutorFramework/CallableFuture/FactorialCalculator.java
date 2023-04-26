package com.concurrency.ExecutorFramework.CallableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/*
    This class is of type Callable().

 */
public class FactorialCalculator implements Callable<Integer> {

    private Integer number;

    public FactorialCalculator(Integer number) {
        this.number = number;
    }

    /*
        Here we are overriding the call() method of Callable
        - we return 1 for the number 0 or 1 w/o hitting the loop.
        - for numbers > 2, we iterate from 2 to number, multiplying the previous result by the new accumulator
        and then waiting 20 ms before continuing
        - after the loop we print the result and return to caller.
     */
    @Override
    public Integer call() throws Exception {
        int result = 1;
        if ((number == 0) || (number == 1)) {
            result = 1;
        } else {
            for (int i = 2; i <= number; i++) {
                result *= i;
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }
        System.out.println("Result for number - " + number + " -> " + result);
        return result;
    }
}
