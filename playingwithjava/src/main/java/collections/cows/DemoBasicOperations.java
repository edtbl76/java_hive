package collections.cows;


import utils.Generated;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*
    ArrayList + LinkedList aren't thread-safe.
    - If we are working in an environment where multiple threads exist, bad things happen.
    - trying to modify elements during iteration -> ConcurrentModificationException

    Solution 1: Vector
        - these suck, because the entire collection is locked during write operations.
        - SLOW

    Solution 2: Collections.synchroniedList()
        - same problem as Vecvtor

    Solution 3: CopyOnWriteArrayList!
 */
@Generated
@SuppressWarnings("all")
public class DemoBasicOperations {

    public static void main(String[] args) {

        /*
         *  Creation
         */

        /*
            1. No Args Constructor
            - creates an internal array w/ size zero
            private transient volatile Object[] array'

            public CopyOnWriteArrayList() {
                setArray(new Object[0]);
            }

            final void setArray(Object[] a) {
                array = a;
            }
         */
        CopyOnWriteArrayList<Integer> cow = new CopyOnWriteArrayList<>();

        /*
            2. Uses an existing array

                public CopyOnWriteArrayList(E[] toCopyIn) {
                    setArray(Arrays.copyOf(toCopyIn, toCopyIn.length, Object[].class));
                }
         */
        Integer[] array = new Integer[]{};
        List<Integer> cowFromArray = new CopyOnWriteArrayList<>(array);

        /*
            3. From existing collection
         */
        List<Integer> cowFromCollection = new CopyOnWriteArrayList<>(cow);

        /*
            Insertion
            - supports same methods as List<>
                - add(E e)      [Doesn't throw ConcurrentModificationException]
                - add(int index, E element)
                - addAll(Collection collection)
         */

        // and some new ones..., but remember
        // in order to use them the Variable must be explicitly of the concrete type, not
        // the List interface.

        // 1. addIfAbsent(): supports individual elements.
        cow.addIfAbsent(10);
        System.out.println("addIfAbsent(): " + cow);

        // 2. addAllAbsent(): supports a collection
        cow.addAllAbsent(List.of(20, 30, 40, 50));
        System.out.println("addAllAbsent(): " + cow);

    }
}
