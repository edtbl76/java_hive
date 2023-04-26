package CollectionsInJava.HashMap.Examples;

import java.util.*;

public class MapSortingExamples1_ByKey {

    public static void main(String[] args) {

        System.out.println("Sort using TreeMap");
        sortByKeyUsingTreeMap();

        System.out.println("Sort using Java8 Streams");
        sortByKeyJava8Stream();
    }

    private static void sortByKeyUsingTreeMap() {
        Map<String, Integer> unsorted = getUnsortedMap();
        System.out.println("Unsorted Map: " + unsorted);

        Map<String, Integer> sorted = new TreeMap<>(unsorted);
        System.out.println("Sorted Map: " + sorted);

        Map<String, Integer> reversed = new TreeMap<>(Collections.reverseOrder());
        reversed.putAll(unsorted);
        System.out.println("Reverse Sorted Map : " + reversed);

    }

    private static void sortByKeyJava8Stream() {
        Map<String, Integer> unsorted = getUnsortedMap();
        System.out.println("Unsorted Map: " + unsorted);

        LinkedHashMap<String, Integer> sorted = new LinkedHashMap<>();
        unsorted.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEachOrdered(stringIntegerEntry -> sorted.put(stringIntegerEntry.getKey(), stringIntegerEntry.getValue()));
        System.out.println("Sorted Map: " + sorted);

        LinkedHashMap<String, Integer>  reversed = new LinkedHashMap<>();
        unsorted.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEachOrdered(stringIntegerEntry -> reversed.put(stringIntegerEntry.getKey(), stringIntegerEntry.getValue()));
        System.out.println("Reverse Sorted Map:  " + reversed);
    }

    private static Map<String, Integer>  getUnsortedMap() {
        Map<String,  Integer> unsorted = new HashMap<>();
        unsorted.put("Ed", 1);
        unsorted.put("Vanessa", 2);
        unsorted.put("Mike", 3);
        unsorted.put("Connor", 4);
        unsorted.put("Unnamed  Dog", 5);
        return unsorted;
    }
}
