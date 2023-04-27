package javaeight.concurrency.stampedlock;

import utils.Generated;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

import static java.util.concurrent.TimeUnit.SECONDS;

@Generated
@SuppressWarnings({"java:S106", "SameParameterValue"})
public class DemoNonBlocking {

    static Map<String, Integer> data = new HashMap<>();
    static StampedLock lock = new StampedLock();

    public static void main(String[] args) throws InterruptedException {

        // 1. tryReadLock() / tryWriteLock() (Immediate)
        write("Vanessa", 25);
        System.out.println(read("Vanessa"));

        // 2. waited versions
        waitedWrite("Michael", 22, 1, SECONDS);
        System.out.println(waitedRead("Michael", 2, SECONDS));


    }

    /*
        tryReadLock()
        - tries to acquire lock immediately, otherwise it won't block.
     */
    private static Integer read(String key) {

        long stamp = lock.tryReadLock();
        int result = 0;

        // in try versions, 0 is a special value used to annotate that
        // we failed to acquire access.
        if (stamp != 0L) {
            try {
                result = data.get(key);
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return result;
    }

    /*
        tryReadLock(time, unit)
     */
    private static Integer waitedRead(String key, long time, TimeUnit unit) throws InterruptedException {
        long stamp = lock.tryReadLock(time, unit);
        int result = 0;

        if (stamp != 0L) {
            try {
                result = data.get(key);
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return result;
    }

    /*
        tryWriteLock
     */
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

    private static void waitedWrite(String key, Integer value, long time, TimeUnit unit) throws InterruptedException {
        long stamp = lock.tryWriteLock(time, unit);

        if (stamp != 0L) {
            try {
                data.put(key, value);
            } finally {
                lock.unlockWrite(stamp);
            }
        }
    }




}
