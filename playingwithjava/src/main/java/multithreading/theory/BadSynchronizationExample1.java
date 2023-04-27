package multithreading.theory;

import utils.Generated;

@Generated
public class BadSynchronizationExample1 {



    /*
        Calling wait() on an object outside of a synchronization block
        will result in an IllegalMonitorStateException: current thread is not owner.
     */
    public static void main(String[] args) throws InterruptedException {

        Object object = new Object();
        object.wait();
    }
}
