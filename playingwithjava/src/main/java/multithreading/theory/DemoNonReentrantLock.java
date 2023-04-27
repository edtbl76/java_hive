package multithreading.theory;

import utils.Generated;

@Generated
public class DemoNonReentrantLock {

    boolean isLocked;

    public DemoNonReentrantLock() {
        this.isLocked = false;
    }

    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            wait();
        }
        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
        notify();
    }

    public static void main(String[] args) throws InterruptedException {
        DemoNonReentrantLock nonReentrantLock = new DemoNonReentrantLock();

        // First locking is successful
        nonReentrantLock.lock();
        System.out.println("Acquired first lock");

        // Second lock causes a self deadlock
        System.out.println("Trying to acquire second lock.");
        nonReentrantLock.lock();

        // This never executes.
        System.out.println("Acquired second lock");
    }
}
