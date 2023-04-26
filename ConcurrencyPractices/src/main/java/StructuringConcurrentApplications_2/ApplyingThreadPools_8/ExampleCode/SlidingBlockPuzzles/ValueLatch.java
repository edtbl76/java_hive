package StructuringConcurrentApplications_2.ApplyingThreadPools_8.ExampleCode.SlidingBlockPuzzles;

import java.util.concurrent.CountDownLatch;

public class ValueLatch<T> {

    /*
        This is the "result" piece of the result-bearing latch
     */
    private T value = null;

    /*
        Instead of building my own (Using techniques from Chapter 14)
        - Use CountDownLatch
        - The Latch has a counter of 1, so we can only set it once before it reaches terminal state.
            - Remember, once threads hit a terminal state, they are in that state forever.

     */
    private final CountDownLatch DONE = new CountDownLatch(1);

    public boolean isSet() {
        return (DONE.getCount() == 0);
    }

    /*
        This is a check-and-set pattern, so it is synchronized.
        - We can only set the value if the CountDownLatch has yet to terminate.
        - We call countDown() to nuke the thread, but we set the value so that an instance of this class
        can persist beyond the life of the thread.
     */
    public synchronized void setValue(T newValue) {
        if (!isSet()) {
            value = newValue;
            DONE.countDown();
        }
    }

    /*
        This calls  the await() method which means it blocks until the CountDownLatch reaches 0.
        - Once the CountDownLatch reaches 0, we return the "result" of the result-bearing latch
        - this has to be synchronized.
            - 1.) this prevents other threads from accessing the value while it is returned.
            - 2.) We're not as concerned about reading the value as we are other threads attempting to change the value
            while it is being returned.
                - if the value were returned and changed at the same time by another thread, the returned value would
                be stale/incorrect/inconsistent.


     */
    public T getValue() throws InterruptedException {
        DONE.await();

        /*
            Synchronized block acts "kinda like" a method.
            - The "this" object is the instance of the ValueLatch (This is the "Monitor Object")
            - The code is SYNCHRONIZED on the Monitor Object

            The benefit of creating sync'd blocks is that it doesn't prevent multiple threads from calling the method
            , but only one thread is allowed to access the sync'd block.


         */
        synchronized (this) {
            return value;
        }
    }
}
