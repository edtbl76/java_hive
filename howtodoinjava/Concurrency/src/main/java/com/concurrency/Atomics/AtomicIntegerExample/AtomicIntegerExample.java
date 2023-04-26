package com.concurrency.Atomics.AtomicIntegerExample;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {

    public static void main(String[] args) {

        // No initial value
        AtomicInteger atomicInteger1 = new AtomicInteger();
        atomicInteger1.set(1); // set
        System.out.println(atomicInteger1.get());

        // With iniitial value
        AtomicInteger atomicInteger2 = new AtomicInteger(2);
        System.out.println(atomicInteger2.get());

        // anonymous
        System.out.println(new AtomicInteger(5));
    }
}
