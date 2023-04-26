package LivenessPerformanceTesting_3.TestingConcurrentPrograms_12.Examples;

import java.util.concurrent.Semaphore;

public class BoundedBuffer<E> {

    /*
        Pair of Counting Semaphores used to control our put and take methods

        availableItems
            - represents number of elements that can be REMOVED from the buffer

        availableSpaces
            - represents the number of elements that can be INSERTED into the buffer.


        NOTE:
        - permits used in counting semaphores are not represented explicitly or associated with
        an owning thread:

            release()       creates a permit
            acquire()       consumes a permit

     */
    private final Semaphore availableItems;
    private final Semaphore availableSpaces;

    /*
        Fixed-length array based buffer's storage
     */
    private final E[] items;


    private int putPosition = 0;
    private int takePosition = 0;

    /*
        Constructor takes a capacity argument to initialize the semaphores
            - availableItems is init'd to 0 because the buffer is empty
            - availableSpaces is inti'd to capacity for the same reason.
            - items creates a new array of size 'capacity'
     */
    public BoundedBuffer(int capacity) {
        availableItems = new Semaphore(0);
        availableSpaces = new Semaphore(capacity);
        items = (E[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return availableItems.availablePermits() == 0;
    }

    public boolean isFull() {
        return availableSpaces.availablePermits() == 0;
    }

    /*
        Works in reverse order from take()

        1.) acquire a permit from availableSpaces semaphore
            - immediately succeeds for an empty buffer
            - this only blocks if isFull() is true.

        2.) inserts the provided element into the buffer

        3.) releases a permit to availableItems, suggesting that elements can
            be removed

     */
    public void put(E e) throws InterruptedException {
        availableSpaces.acquire();
        doInsert(e);
        availableItems.release();
    }

    /*
        1.) acquire a permit from availableItems semaphore
        - immediately succeeds for a nonempty buffer
        - else blocks until semaphore becomes non-empty (W/o a timeout this could wait a while)

        2.) once permit is obtained, we get the next element from the buffer

        3.) permit is released to availableSpaces semaphore.
     */
    public E take() throws InterruptedException {
        availableItems.acquire();
        E item = doExtract();
        availableSpaces.release();
        return item;
    }

    private synchronized void doInsert(E e) {
        int i = putPosition;
        items[i] = e;
        putPosition = (++i == items.length) ? 0 : i;
    }

    private synchronized E doExtract() {
        int i = takePosition;
        E e = items[i];
        items[i] = null;
        takePosition = (++i == items.length) ? 0 : i;
        return e;
    }
}
