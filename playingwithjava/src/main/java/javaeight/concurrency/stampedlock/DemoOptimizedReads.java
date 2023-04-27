package javaeight.concurrency.stampedlock;

import utils.Generated;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;

@Generated
@SuppressWarnings("java:S106")
public class DemoOptimizedReads {

    static Map<String, Integer> data = new HashMap<>();
    static StampedLock lock = new StampedLock();

    public static void main(String[] args) {
        write("Gravy", 4);
        System.out.println(read("Gravy"));
    }

    /*
        PROBLEM
        - data read frequently/ rarely updated.
        - acquire/release of a lock is costly and can lead to starvation.
        - avoid getting a read lock every time we are reading.

            SCENARIO

                Thread1 tries to get optimistic lock

                1. if some other thread already has a write lock, thread1 returns. It can't get the lock
                2. if some other thread already has a read lock, tryOptimisticRead() will return an
                    OBSERVATION STAMP
                    - this is NOT a lock
                3. Thread1 completes its read, and calls validate(stamp)
                    - this determines if a write occurred after the generation of the OBSERVATION STAMP
                4. if validate succeeds, it means the data is the most recent and we can return teh result
                5. if it fails, it means the data has been modified and our data is stale, we have
                    to do something else.

     */

    @SuppressWarnings("SameParameterValue")
    private static Integer read(String key) {
        int result = 0;
        long stamp = lock.tryOptimisticRead();

        if (stamp != 0L) result = data.get(key);

        if (!lock.validate(stamp)) {
            // This means that the data was modified AFTER we called optimistic read.
            // we do extra work here to get the latest data.
            result = data.get(key);
        }

        return result;

    }

    @SuppressWarnings("SameParameterValue")
    private static void write(String key, Integer value) {
        long stamp = lock.tryWriteLock();

        if (stamp != 0L) {
            try {
                data.put(key, value);
            } finally {
                lock.unlockWrite(stamp);
            }
        }
    }
}
