package collections.hashset;

import utils.Generated;

import java.util.HashSet;
import java.util.Set;


/*
    - allows 1 null element
    - stored in random order
    - backed by a HashMap

 */
@Generated
@SuppressWarnings("all")
public class DemoBasic {

    public static void main(String[] args) {

        /*
            1. No Args Constructor
            - initial capacity defaults to 16
            - load factor is 0.75

            LOAD FACTOR: defines when the Set should be resized.
                - a load factor of .75, means that the Set will be resized when it is
                75% full.
                - for default, this means 12 elements exist.
         */
        HashSet<Integer> set = new HashSet<>();


        /*
            2. update initial capacity
            - load factor remains 75%
         */
        set = new HashSet<>(128);

        /*
            3. updates both initial capacity and load factor
            (maybe we reduce load factor, because the system fills up quickly)
         */
        set = new HashSet<>(128, 0.5f);


        /*
            4. Init w/ existing Set
         */
        set = new HashSet<>(Set.of(1, 2, 3));
        System.out.println("New Set: " + set);

        /*
            Insertion
            1. adding an element
         */
        set.add(4);
        set.add(5);
        System.out.println("add() - Updated Set: " + set);

        /*
            Fetching Values
            - There is no get() because HashSets are backed by a map() and not an array().
            - we can only check if it contains.
         */
        System.out.println("contains() - Set contains 5: " + set.contains(5));

        /*
         *   Remove
         *  - Since the underlying structure is a map, we can use
         *      int primitives w/o them being misconstrued as an index.
         */
        // 1. remove
        set.remove(5);
        System.out.println("remove() " + set);

        // 2. clear()
        set.clear();
        System.out.println("clear() " + set);
        System.out.println("isEmpty() ? " + set.isEmpty());

    }
}
