package com.concurrency.Atomics.AtomicIntegerExample;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounterMethodExamples {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(100);
        System.out.println("Initial Value: " + atomicInteger.get());

        System.out.println("\naddAndGet()");
        System.out.println(atomicInteger.addAndGet(5));
        System.out.println(atomicInteger.get());

        System.out.println("\ngetAndAdd()");
        System.out.println(atomicInteger.getAndAdd(5));
        System.out.println(atomicInteger.get());

        System.out.println("\nincrementAndGet()... like ++i");
        System.out.println(atomicInteger.incrementAndGet());
        System.out.println(atomicInteger.get());

        System.out.println("\ngetAndIncrement()... like i++");
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.get());

        System.out.println("\ndecrementAndGet()... like --i");
        System.out.println(atomicInteger.decrementAndGet());
        System.out.println(atomicInteger.get());

        System.out.println("\ngetAndDecrement()... like i--");
        System.out.println(atomicInteger.getAndDecrement());
        System.out.println(atomicInteger.get());




    }
}
