package multithreading.theory;

import utils.Generated;

@Generated
public class BadSynchronizationExample2 {

    public static void main(String[] args) {
        Object object = new Object();
        Object lock = new Object();

        /*
            Trying to call notify() on an object in the synchronized
            block of another object results in

                IllegalMonitorStateException: current thread is not owner.
         */
        synchronized (lock) {
            object.notify();
        }
    }
}
