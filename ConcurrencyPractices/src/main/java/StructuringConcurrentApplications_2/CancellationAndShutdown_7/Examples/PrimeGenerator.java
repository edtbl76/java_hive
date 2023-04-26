package StructuringConcurrentApplications_2.CancellationAndShutdown_7.Examples;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PrimeGenerator  implements Runnable {

    private final List<BigInteger> primes = new ArrayList<>();

    // Cancellation Request Flag
    private volatile boolean cancellationRequestFlag;

    @Override
    public void run() {
        BigInteger prime = BigInteger.ONE;
        while (!cancellationRequestFlag) {
            prime = prime.nextProbablePrime();
            synchronized (this) {
                primes.add(prime);
            }
        }
    }

    public void cancel() {
        cancellationRequestFlag = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<>(primes);
    }
}
