package collections.arraylists;

import utils.Generated;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static java.lang.Integer.valueOf;

@Generated
public class DemoBasicOperations {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {

        /*
         *  Creation
         */

        // 1. No args Constructor
        List<Integer> list1 = new ArrayList<>();

        // 2. Constructor that takes initial capacity (prevents frequent resizing)
        List<Integer> list2 = new ArrayList<>(50);

        // 3. Using Old Collection
        List<Integer> list3 = new ArrayList<>(list1);


        /*
         *  Insertion
         */
        // 1. Insert at end
        list3.add(10);
        list3.add(12);
        System.out.println(list3);

        // 2. Insert at given index
        list3.add(1, 11);
        System.out.println(list3);

        // 3. Insert multiple elements from another collection
        list2.addAll(List.of(7, 8, 9));
        System.out.println(list2);

        // 4 Insert multiple elements from another collection at given index
        list3.addAll(0, list2);
        System.out.println(list3);

        /*
         *  Retrieval / Fetch
         */
        // 1. get size
        System.out.println(list3.size());

        // 2. get values
        System.out.println(list3.get(1));

        /*
         *  Delete / Removal
         */
        // 1. Delete By Index
        list3.remove(list3.size() - 1);
        System.out.println(list3);

        // 2. Remove first occurence of an object
        list3.addAll(list2);
        System.out.println("Before Remove: " + list3);

        list3.remove(valueOf(7));
        System.out.println("After Remove: " + list3);

        // 3. Remove w/ Predicate (All even numbers in this example)
        list3.removeIf(integer -> (integer % 2) == 0);
        System.out.println(list3);

        // 4. Remove All (Note; decent hack here. If you want to
        // remove all instances of a number, just call removeAll w/ a list of that number.
        list3.removeAll(List.of(9, 7));
        System.out.println(list3);

        // 5. Clear
        list3.clear();
        System.out.println(list3);

        /*
         * Updating
         */
        //1. Replacing w/ a predicate
        List<String> list4 = new ArrayList<>();
        list4.add("hello");
        list4.add("world");
        System.out.println(list4);
        list4.replaceAll(String::toUpperCase);
        System.out.println(list4);

        // 2. Explicitly setting a value at a given index
        list4.set(0, "GOODBYE");
        System.out.println(list4);

        /*
         * CONTAINS
         */
        list1.addAll(List.of(10, 20, 30, 40, 10));
        System.out.println(list1);

        // 1. Contains single value
        System.out.println(list1.contains(20));

        // 2. Contains a collection
        // (Wrapped in HashSet for performance purposes)
        System.out.println(new HashSet<>(list1).containsAll(List.of(10, 20, 40)));

        // 3. check first and last index
        System.out.println(list1.indexOf(10));
        System.out.println(list1.lastIndexOf(10));

    }
}
