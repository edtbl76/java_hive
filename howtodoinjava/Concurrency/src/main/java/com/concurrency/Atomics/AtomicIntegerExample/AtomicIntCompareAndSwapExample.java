package com.concurrency.Atomics.AtomicIntegerExample;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntCompareAndSwapExample {

    /*
        COMPARE AND SWAP
            - operation that compares the contents of a memory location to a given value
            - if the two values are the same, the operation modifies the contents of the memory location to the new value.

            NOTE: This is done in a single ATOMIC OPERATION
                - atomicity guarantees that the new value is calculated based on UP TO DATE information.
                - if value had been updated by another thread in the meantime, the WRITE WOULD FAIL



     */
    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(100);

        // Successful CAS
        boolean isSuccess = atomicInteger.compareAndSet(100,110);
        System.out.println(isSuccess);

        // Failed CAS
        isSuccess = atomicInteger.compareAndSet(100, 120);
        System.out.println(isSuccess);

    }
}
