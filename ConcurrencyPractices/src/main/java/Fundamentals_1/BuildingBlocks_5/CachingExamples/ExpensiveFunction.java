package Fundamentals_1.BuildingBlocks_5.CachingExamples;

import java.math.BigInteger;

/*
    Concrete implementation of Computable
    - long-running compute task.
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String argument) throws InterruptedException {
        Thread.sleep(10000);
        return new BigInteger(argument);
    }
}
