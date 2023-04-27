package collections.collectionsclass;

import utils.Generated;

import java.util.*;

import static java.util.Collections.*;

@Generated
@SuppressWarnings("java:S106")
public class DemoUnmodifiableCollections {

    /*
        Unmodifiable Collections return special versions of collections
        that return 'UnsupportedOperationException' when trying to add/remove
        elements
     */
    @SuppressWarnings("DuplicatedCode")
    public static void main(String[] args) {

        // Initial Structures
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3));
        Set<Integer> set = new HashSet<>(Set.of(1, 2, 3));
        SortedSet<Integer> sortedSet = new TreeSet<>(Set.of(3, 2, 1));
        Map<String, Integer> map = new HashMap<>(Map.of("One", 1, "Two", 2, "Three", 3));
        SortedMap<String, Integer> sortedMap = new TreeMap<>(map);

        // Unmodifiable versions
        List<Integer> unmodifiableList = unmodifiableList(list);
        Set<Integer> unmodifiableSet = unmodifiableSet(set);
        SortedSet<Integer> unmodifiableSortedSet = unmodifiableSortedSet(sortedSet);
        Map<String, Integer> unmodifiableMap = unmodifiableMap(map);
        SortedMap<String, Integer> unmodifiableSortedMap = unmodifiableSortedMap(sortedMap);

        System.out.println(unmodifiableList);
        System.out.println(unmodifiableSet);
        System.out.println(unmodifiableSortedSet);
        System.out.println(unmodifiableMap);
        System.out.println(unmodifiableSortedMap);

    }
}
