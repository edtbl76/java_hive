package collections.arraylists;

import utils.Generated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@SuppressWarnings("all")
@Generated
public class DemoSort {

    public static void main(String[] args) {

        // Setup
        List<Integer> list = new ArrayList<>(List.of(34, 12, 9, 76, 29, 75));

        // 1. Collections sort
        System.out.println("Pre Sort: " + list);
        // make a copy w/ the copy constructor so that the original list can remain unsorted
        // for future demo sections
        List<Integer> listCopy = new ArrayList<>(list);
        Collections.sort(listCopy);
        System.out.println("Sorted  :" + listCopy);

        // 2. Streams Sort Example
        // (This doesn't mutate the original list)
        List<Integer> sortedList = list.stream().sorted().collect(Collectors.toList());
        System.out.println("Stream Sorted  :" + sortedList);

        // 3. Same example, but newer syntax
        List<Integer> sortedList2 = list.stream().sorted().toList();
        System.out.println("Stream Sorted  :" + sortedList2);

        // 4. Reverse Order (Collections Sort)
        listCopy = new ArrayList<>(list);
        Collections.sort(listCopy, Collections.reverseOrder());
        System.out.println("Sorted (Desc): " + listCopy);

        // 5. Reverse Order (Streams API w/ Comparator) -> Skipped just to newer syntax.
        // Again - no mutation.
        List<Integer> reverseSort = list.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println("Sorted (Desc): " + reverseSort);

        System.out.println("Original Order Still Retained: " + list);


    }
}
