package LivenessPerformanceTesting_3.TestingConcurrentPrograms_12.Examples.tests;

import LivenessPerformanceTesting_3.TestingConcurrentPrograms_12.Examples.BoundedBuffer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoundedBufferTest {

    public static final Integer LOCKUP_DETECT_TIMEOUT = 0;
    /*
        First 2 tests are sequential "low hanging fruit" type tests.
     */
    @Test
    void testIsEmptyWhenConstructed() {
        BoundedBuffer<Integer> boundedBuffer = new BoundedBuffer<>(10);
        assertTrue(boundedBuffer.isEmpty());
        assertFalse(boundedBuffer.isFull());
    }

    @Test
    void testIsFullAfterPuts() throws InterruptedException {
        BoundedBuffer<Integer> boundedBuffer = new BoundedBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            boundedBuffer.put(i);
        }
        assertTrue(boundedBuffer.isFull());
        assertTrue(boundedBuffer.isEmpty());
    }

    @Test
    void testTakeBlocksWhenEmpty() {
        final BoundedBuffer<Integer> boundedBuffer = new BoundedBuffer<>(10);

        /*
            This is a lambda-fied Runnable (i.e. void run()) declared as a new Thread().

            - It attempts to take() an element from an empty buffer.
            - if that actually works, then something went wrong, because take() should fail ifEmpty().
                - (so we call fail() from JUnit 5)

            - The EXPECTED behavior is that take() is going to block.
                - This should throw an InterruptedException.
                - This is why the catch() {} block treats the capturing of this exception as success.
                - NOTE: The thread will be allowed to exit at this point.. "unblocking" the tested take() action.

         */
        Thread taker = new Thread(() -> {
            try {
                int unused = boundedBuffer.take();

                // If we get to this point, it's an error
                fail();
            } catch (InterruptedException success) {
                // Success!
                // Thread is allowed to exit, which solves the "unblocking" challenge.
            }
        });


        /*
            This is the actual TEST section:

                1.) start the thread we created.

                2.) sleep for the amount of time we expect it take to for the thread to block

                3.) If the thread has blocked, then calling taker.interrupt() will cause the
                    InterruptedException
                    - the catch block is a no-op, so the thread will exit (fairly) gracefully

                    FALSE POSITIVE:
                    - while HIGHLY unlikely, it is possible for something to go wrong that leads the thread
                    to terminate prematurely (i.e. before the interrupt()).
                        - This will appear as a successful test, even though the invariant being targetted hasn't
                        been properly validated

                4.) taker.join()
                    - if the taker thread responded properly to step 3, then it has already terminated, therefore
                    join() returns immediately.

                    - FAILURE CASES
                        - There is a possible condition where the InterruptedException has thrown, but the
                        thread hasn't terminated yet, in which case, join() will throw an InterruptedException.

                        - If take() succeeded, we short circuit the try-block in the Runnable lambda to fail()

                5.) We validate the previous step by calling Thread.isAlive()

                    If the thread is still alive, then the test fails.

         */
        try {
            taker.start();
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            taker.interrupt();
            taker.join(LOCKUP_DETECT_TIMEOUT);
            assertFalse(taker.isAlive());
        } catch (Exception unexpected) {
            fail();
        }
    }

}
