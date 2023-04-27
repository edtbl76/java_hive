package multithreading.concurrentcollections;

import utils.Generated;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.System.nanoTime;

/*
    COW - suitable for read-heavy use cases

    How it works.
    - immutable copy is created of the backing collection

    WRITES:
        - Copy is discarded and a new copy w/ the change is created.

    READS:
        - no sync is required.

 */
@Generated
@SuppressWarnings("all")
public class DemoCopyOnWrite {

    public static void main(String[] args) {

        // Initialize non concurrent ArrayList
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.ensureCapacity(500_000);

        // Initialize new Copy on Write ArrayList w/ same capacity
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>(arrayList);

        // Calc time of normal arrayList
        long startTime = nanoTime();
        arrayList.add(500_001);
        long endTime = nanoTime();
        long duration = endTime - startTime;
        System.out.println("Time taken by ArrayList: "+ duration + " nano seconds");


        // Calculate time it takes to add numbers in COWArraylist
        startTime = nanoTime();
        copyOnWriteArrayList.add(500_001);
        endTime = nanoTime();
        duration = endTime - startTime;
        System.out.println("Time taken by COW ArrayList: "+ duration + " nano seconds");
    }
}
