package collections.collectionsclass;

import utils.Generated;

import java.util.*;

import static java.util.Collections.*;

@Generated
@SuppressWarnings("java:S106")
public class DemoSynchronizedCollections {

    /*

     */
    @SuppressWarnings("DuplicatedCode")
    public static void main(String[] args) {

        // Initial Structures
        List<Integer> list = new ArrayList<>(List.of(3, 2, 1));
        Set<Integer> set = new HashSet<>(Set.of(2, 3, 1));
        SortedSet<Integer> sortedSet = new TreeSet<>(set);
        Map<String, Integer> map = new HashMap<>(Map.of("One", 1, "Two", 2, "Three", 3));
        SortedMap<String, Integer> sortedMap = new TreeMap<>(map);

        // Synchronized Versions
        List<Integer> synchronizedList = synchronizedList(list);
        Set<Integer> synchronizedSet = synchronizedSet(set);
        SortedSet<Integer> synchronizedSortedSet = synchronizedSortedSet(sortedSet);
        Map<String, Integer> synchronizedMap = synchronizedMap(map);
        SortedMap<String, Integer> synchronizedSortedMap = synchronizedSortedMap(sortedMap);

        // Print 'em
        System.out.println(synchronizedList);
        System.out.println(synchronizedSet);
        System.out.println(synchronizedSortedSet);
        System.out.println(synchronizedMap);
        System.out.println(synchronizedSortedMap);


    }
}
