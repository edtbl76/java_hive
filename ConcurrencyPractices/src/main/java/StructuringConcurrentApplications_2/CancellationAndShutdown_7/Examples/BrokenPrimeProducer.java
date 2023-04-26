package StructuringConcurrentApplications_2.CancellationAndShutdown_7.Examples;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BrokenPrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;
    private volatile boolean cancellationRequestFlag = false;

    public BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger prime = BigInteger.ONE;
            while (!cancellationRequestFlag) {
                queue.put(prime = prime.nextProbablePrime());
            }
        } catch (InterruptedException consumed) {}
    }

    public void cancel() {
        cancellationRequestFlag = true;
    }
}

class BrokenPrimeProducerUtil {

    void consumePrimes() throws InterruptedException {
        BlockingQueue<BigInteger> primes = new ArrayBlockingQueue<>(10);
        BrokenPrimeProducer producer = new BrokenPrimeProducer(primes);
        producer.start();
        try {
            while (needMorePrimes()) {
                consume(primes.take());
            }
        } finally {
            producer.cancel();
        }

    }

    // Faux code to eliminate errors in the example above.
    private void consume(BigInteger take) {
    }

    private boolean needMorePrimes() {
        return Boolean.TRUE;
    }
}


