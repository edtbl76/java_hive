package collections.collectionsclass;

import utils.Generated;

import java.util.*;

import static java.util.Collections.*;
import static java.util.Comparator.*;

@Generated
@SuppressWarnings("java:S106")
public class DemoBasicOperations {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>(List.of(9, 34, 12, 9, 76, 3, 29, 9, 75));
        System.out.println("Initial List: " + list);

        // Collections.min()
        System.out.println("The minimum element is " + min(list));

        // Collections.max()
        System.out.println("The maximum element is " + max(list));

        // Collections.frequency()
        System.out.println("The frequency of 9 in the list is " + frequency(list, 9));

        /*
            Collections.binarySearch(List list, T key)
            - same as Array Search
            - 1. elements must implement Comparable interface
            - 2. list must already be sorted in ascending order or the results will be undefined.
         */
        List<Integer> sortedList = new ArrayList<>(new HashSet<>(list));
        sortedList.sort(comparing(Integer::intValue));
        System.out.println("Sorted List: " + sortedList);

        // no guarantee which one is found if we search for an element that occurs multiple times.
        // ... I ran it 100 fucking times and it came out 1 every time.
        System.out.println("Index of 9 might be... " + binarySearch(sortedList, 9));

        /*
            Collections.binarySearch(List list, T key, Comparator comparator)
            - this can be used for objects/elements that don't implement Comparable
         */
        System.out.println("Index of 9 might be... " + binarySearch(sortedList, 9, comparing(Integer::intValue)));


        /*
            Copy list
            - copy elements of source to destination
            - if destination is too small, IndexOutOfBoundsException is thrown
         */
        List<Integer> source = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> destination = new ArrayList<>(List.of(10, 20, 30, 40, 50));
        System.out.println("Source      : " + source);
        System.out.println("Destination : " + destination);

        // What a stupid syntax...
        copy(destination, source);
        System.out.println("Source      (After) : " + source);
        System.out.println("Destination (After) : " + destination);

        /*
            fill(List list, Object obj) -> w/ defaults
         */
        System.out.println("Initial List: " + sortedList);
        fill(sortedList, 0);
        System.out.println("Filled List: " + sortedList);



    }
}
