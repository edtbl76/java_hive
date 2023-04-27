package collections.hashset;

import utils.Generated;

import java.util.*;

import static java.util.Comparator.comparing;

@Generated
@SuppressWarnings("all")
public class DemoIterationAndSort {

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>(Set.of(23, 34, 56));

        System.out.println("Starting Set: " + set);

        // 1. Using an Iterator
        System.out.println("Iterator: ");
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println();

        // 2. Enhanced For
        System.out.println("Enhanced For: ");
        for (Integer integer : set){
            System.out.println(integer);
        }
        System.out.println();

        // 3. For Each
        System.out.println("ForEach: ");
        set.forEach(System.out::println);
        System.out.println();

        // 4. Streams
        System.out.println("forEachRemaining: ");
        set.iterator().forEachRemaining(System.out::println);
        System.out.println();

        /*
         *  HashSets must be converted to a list in order to be sorted.
         */

        // 1. Streams. I prefer this because
        List<Integer> list = set.stream().sorted().toList();

        System.out.print("Stream Sort: ");
        list.forEach(integer -> System.out.print(integer + " "));
        System.out.println();

        System.out.print("Original Set: ");
        set.forEach(integer -> System.out.print(integer + " "));
        System.out.println();

        // 2. Copy Constructor
        list = new ArrayList<>(set);
        list.sort(comparing(integer -> integer));
        System.out.print("Copy Sort: ");
        list.forEach(integer -> System.out.print(integer + " "));
        System.out.println();

        System.out.print("Original Set: ");
        set.forEach(integer -> System.out.print(integer + " "));
        System.out.println();

    }
}
