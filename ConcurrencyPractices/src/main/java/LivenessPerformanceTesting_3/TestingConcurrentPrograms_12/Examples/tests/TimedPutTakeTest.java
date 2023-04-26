package LivenessPerformanceTesting_3.TestingConcurrentPrograms_12.Examples.tests;

import LivenessPerformanceTesting_3.TestingConcurrentPrograms_12.Examples.BarrierTimer;
import LivenessPerformanceTesting_3.TestingConcurrentPrograms_12.Examples.BoundedBuffer;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimedPutTakeTest {

    /*
        newCachedThreadPool
        - See definition in 8.3
     */
    private static final ExecutorService pool = Executors.newCachedThreadPool();

    private final AtomicInteger putSum = new AtomicInteger(0);
    private final AtomicInteger takeSum = new AtomicInteger(0);


    /*
        A Cyclic Barrier is a type of synchronizer that allows a set of threads to wait
        for each other to reach a common execution point (called the Barrier).

        - The barrier is basically where the threads "meet up" before continuing any further execution (or
        termination).

        - (Remember, a Synchronizer is an object that represents a class used for common interaction patterns
        between threads)


     */
    private final CyclicBarrier barrier;
    private final BoundedBuffer<Integer> boundedBuffer;
    private final BarrierTimer timer;

    private final int nTrials;

    // N (used to define the number of Producer/Consumer Pairs)
    private final int nPairs;

    public TimedPutTakeTest(int capacity, int nTrials, int nPairs) {
        this.boundedBuffer = new BoundedBuffer<>(capacity);
        this.nTrials = nTrials;
        this.nPairs = nPairs;
        this.timer = new BarrierTimer();
        /*
            This shows that we've modified the constructor we are calling from PutTakeTest, by specificying a
            "barrier action"

                A barrier action is like a "macro" or "job" that is triggered each time we hit the barrier.
         */
        this.barrier = new CyclicBarrier(nPairs * 2 + 1, timer);
    }


    /*
        This is our Test Runner

        1.) We start N producers and N consumer by calling Executor.execute(Runnable)

        2.) The first await() call waits for all of the Producers and Consumers to be ready
            (This means that all of the Producers and Consumers hit their first barrier.await() call)

                I call this the "Readiness Barrier"

            Once all of the Producers and Consumers call await(), then the Producers start producing and the
            consumers start consuming.

        3.) The second await() call waits for all of the Producers and Consumers to be complete.

     */
    void test() {
        try {
            timer.clear();
            for (int i = 0; i < nPairs; i++) {
                pool.execute(new Producer());
                pool.execute(new Consumer());
            }

            // Wait for all threads to be ready
            barrier.await();

            // Wait for all threads to be complete
            barrier.await();


            long nsPerItem = timer.getTime() / (nPairs * (long)nTrials);
            System.out.print("Throughput: " + nsPerItem + " ns/item");
            /*
                If the values in our two atomic integers are equivalent then we know that
                    1.) all of the elements we put were able to be taken
                    2.) since we checksummed the results, we also validate that nothing accidentally leaked
                    in to the data structure.
             */
            assertEquals(putSum.get(), takeSum.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
        MAIN METHOD
        - This is the "test runner"
            - We instantiate the PutTakeTest class w/ the sample parameters
                - (We also call the test() method at the same time)

        - Once the test is completed, we shutdown our thread pool
     */
    public static void main(String[] args) throws Exception {
        int trialsPerThread = 100000;

        for (int cap = 1; cap <=1000; cap *=10) {
            System.out.println("Capacity: " + cap);

            for (int pairs = 1; pairs <= 128; pairs *= 2) {
                TimedPutTakeTest tptt = new TimedPutTakeTest(cap, pairs, trialsPerThread);

                System.out.print("Pairs: " + pairs + "\t");
                tptt.test();

                System.out.print("\t");
                tptt.test();

                System.out.println();
                Thread.sleep(1000);
            }
        }
        pool.shutdown();
    }


    /*
        Our Runnable Producer
     */
    class Producer implements Runnable {
        @Override
        public void run() {
            try {

                /*
                    Our seed value is an xor between the hashcode of this object and the current System.nanoTime()
                    - This gives us enough randomness for the purposes of the app.
                        - this.hashCode() is different for each new Thread, and naturally nanoTime is going to
                        be slightly different for each new thread.
                 */
                int seed = (this.hashCode() ^ (int)System.nanoTime());
                int sum = 0;

                /*
                    This synchronizes w/ the readiness barrier in test()
                 */
                barrier.await();

                /*
                    - We work backwards through the BoundedBuffer structure we've created.
                    - put the current seed value in the buffer
                    - add the seed to our "sum variable"
                    - put the seed through Mastaglia's RNG algorithm.

                 */
                for (int i = nTrials; i > 0; --i) {
                    boundedBuffer.put(seed);
                    sum += seed;
                    seed = xorShift(seed);
                }

                /*
                    Perform our atomic "check and set" and
                    store it in the "put" AtomicInteger
                 */
                putSum.getAndAdd(sum);

                /*
                    Synchronize w/ our "all done" barrier.
                 */
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            try {

                /*
                    This synchronizes w/ the readiness barrier in test().
                    - For a consumer, there is no pre-setup, so we are immediately ready.
                 */
                barrier.await();

                /*
                    Init our sum var.
                 */
                int sum = 0;

                /*
                    Work our way backwards through the buffer.
                    - take each value and add it to sum.
                 */
                for (int i = nTrials; i > 0; i--) {
                    sum += boundedBuffer.take();
                }

               /*
                    Perform our atomic "check and set" and
                    store it in the "take" AtomicInteger
                 */
                takeSum.getAndAdd(sum);

               /*
                    Synchronize w/ our "all done" barrier.
                */
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    static int xorShift(int y) {

        /*
           XOR y vs. y left-shifted by 6 bits
         */
        y ^= (y << 6);

        /*
            XOR NEW y vs. right-shifted by 21 bits (UNSIGNED)
            - this is unsigned so that we zero out rather than go negative
         */
        y ^= (y >>> 21);

        /*
            XOR NEW y vs. left shifted by 7 bits
         */
        y ^= (y << 7);
        return y;
    }

}
