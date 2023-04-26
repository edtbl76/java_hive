package StructuringConcurrentApplications_2.CancellationAndShutdown_7.Examples;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

public class PrimeProducer  extends Thread {

    private final BlockingQueue<BigInteger> queue;

    public PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger prime = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted()) {
                queue.put(prime = prime.nextProbablePrime());
            }
        } catch (InterruptedException e) {
            // Do shit with e
        }
    }

    public void cancel() {
        interrupt();
    }
}
