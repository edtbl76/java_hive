package CollectionsInJava.HashMap.Examples;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapSortingExamples2_ByValue {

    public static void main(String[] args) {
        System.out.println("Sorting using Java8 Streams");
        sortByValueJava8Stream();
    }

    private static void sortByValueJava8Stream() {
        Map<String, Integer> unsorted = getUnsortedMap();
        System.out.println("Unsorted Map : " + unsorted);

        LinkedHashMap<String, Integer> sorted = new LinkedHashMap<>();
        unsorted.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEachOrdered(stringIntegerEntry -> sorted.put(stringIntegerEntry.getKey(), stringIntegerEntry.getValue()));
        System.out.println("Sorted Map   : " + sorted);

        LinkedHashMap<String, Integer> reversed = new LinkedHashMap<>();
        unsorted.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(stringIntegerEntry -> reversed.put(stringIntegerEntry.getKey(), stringIntegerEntry.getValue()));
        System.out.println("Reverse Sorted Map : " + reversed);
    }

    private static Map<String, Integer> getUnsortedMap() {
        Map<String, Integer> unsorted = new HashMap<>();
        unsorted.put("Ed", 1);
        unsorted.put("Vanessa", 2);
        unsorted.put("Mike", 3);
        unsorted.put("Connor", 4);
        unsorted.put("Unnamed Dog", 5);
        return unsorted;
    }
}
