package collections.treeset;


import utils.Generated;

import java.util.Set;
import java.util.TreeSet;

import static java.util.Comparator.comparing;

/*
    TreeSet
    - no null elements allowed.
    - stored in sorted order
        - the storing elements should implement Comparable or a custom Comparator
    - slower than HashSet for operations like add(), remove(), contains() , size() etc.
    - backed by a treemap
 */
@SuppressWarnings("all")
@Generated
public class DemoBasics {

    public static void main(String[] args) {

        /*
         * Creation
         */

        // 1. no args constructor
        Set<Integer> set = new TreeSet<>();

        // 2. If objects being stored don't impl Comparable, we can provide a Comparator as an
        // arg to the constructor
        Set<Integer> setComparator = new TreeSet<>(comparing(integer -> integer));

        // 3. created from another collection
        TreeSet<Integer> setCollection = new TreeSet<>(Set.of(5, 7, 1, 3));

        // (Note even though I provided an unsorted set, the TreeSet sorted it)
        System.out.println("Starting: " + setCollection);

        /*
         *  Insertion;
         */
        // 1. add(E e) (like HashSet, returns true if it was inserted, false if already present)
        System.out.println("add(4) - True: " + setCollection.add(4));
        System.out.println("add(5) - False: " + setCollection.add(5));

        // 2. addAll() - Only adds unique elements.
        setCollection.addAll(Set.of(1, 2, 4, 6, 8));
        System.out.println("addAll(): " + setCollection);

        /*
         *  Fetch/Retrieval
         *  - throws NoSuchElementException when trying to fetch from an empty set
         */
        // 1. fetch first element
        System.out.println("first() " + setCollection.first());

        // 2. fetch last element
        System.out.println("last() " + setCollection.last());

        // 3. fetch elements within a given range
        System.out.println("subSet(5, 10) " + setCollection.subSet(5, 10));

        // 4. fetch elements less than
        System.out.println("headSet(5) " + setCollection.headSet(5));

        // 5. fetch elements greater than
        System.out.println("tailSet(5) " + setCollection.tailSet(5));

        /*
         *  Removal works the same as most sets.
         * - true if it was removed
         * - false if not present
         */
        System.out.println("remove(1) " + setCollection.remove(1));
        System.out.println("remove(9) " + setCollection.remove(9));

        /*
         *  Odds and Ends
         */
        System.out.println("isEmpty() " + setCollection.isEmpty());
        System.out.println("size() " + setCollection.size());
        System.out.println("contains(7) " + setCollection.contains(7));
        System.out.println("contains(10) " + setCollection.contains(10));
    }
}
