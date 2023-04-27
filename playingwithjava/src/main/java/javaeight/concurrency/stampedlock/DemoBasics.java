package javaeight.concurrency.stampedlock;

import utils.Generated;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;

@Generated
@SuppressWarnings({"java:S106", "SameParameterValue"})
public class DemoBasics {

    static Map<String, Integer> data = new HashMap<>();
    static StampedLock lock = new StampedLock();
    public static void main(String[] args) {

        //1. basic reads and writes.
        writeData("Edward", 46);
        System.out.println("My Data: " + readData("Edward"));

    }


    /*
        readLock() + unlockRead()
            - acquires read lock
            - returns stamp that should be used while releasing the lock
     */
    private static Integer readData(String key) {

        long stamp = lock.readLock();
        try {
            return data.get(key);
        } finally {
            lock.unlockRead(stamp);
        }

    }

    /*
        writeLock() & unlockWrite()
        - same semantics as readLock(), but for writes
     */
    private static void writeData(String key, Integer value) {
        long stamp = lock.writeLock();
        try {
            data.put(key, value);
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    /*
        ReentrantReadWriteLock
        - pre Java 8 used for thread-safe reads/writes.

            KEY POINTS
            1. multiple threads can acquire read lock simultaneously
            2. only one thread can acquire a write lock
            3. if a thread is waiting for a write, and there are threads that have a read lock, the thread will
                wait until all the threads release the read lock.

           DRAWBACKS
           1. it can lead to starvation
           2. can be significantly slower than other synchronizers.


          StampedLock improvements.

            - provides separate/isolated read and write locks.
            - supports optimistic locking for read operations.

            - provides method to upgrade a read lock to a write lock.

            - 3 locking modes
                - Read, Write, Optimistic Read
     */



}
