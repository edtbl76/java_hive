package collections.treemaps;


import utils.Generated;

import java.util.*;
import java.util.Map.Entry;


@Generated
@SuppressWarnings("java:S106")
public class SortHashMapWithTreeMap {

    public static void main(String[] args) {

        Map<Integer, String> gameShows = new HashMap<>(
                Map.of(
                        9600, "The Price Is Right",
                        8200, "Jeopardy!",
                        7250, "Wheel of Fortune",
                        4532, "Pyramid",
                        3304, "Family Feud"
                ));

        System.out.println("Unsorted: " + gameShows);

        //1. use a TreeMap copy constructor
        TreeMap<Integer, String> treeMap = new TreeMap<>(gameShows);
        System.out.println("TreeMap constructor: " + treeMap);

        //2. use a TreeMap putAll
        treeMap = new TreeMap<>();
        //noinspection CollectionAddAllCanBeReplacedWithConstructor
        treeMap.putAll(gameShows);
        System.out.println("TreeMap putAll() : " + treeMap);

        //3. Sorting keys and values separately
        List<Integer> keys = new ArrayList<>(gameShows.keySet());
        List<String> values = new ArrayList<>(gameShows.values());

        Collections.sort(keys);
        Collections.sort(values);

        System.out.println("List of sorted keys: " + keys);
        System.out.println("List of sorted values: " + values);

        /*
         *  Streams introduced two really cool comparators
         *  - comparingByKey()
         *  - comparingByValue()
         *
         * I prefer to create vars for them, and then stuff them in
         * the functional code to make it more terse.
         */
        Comparator<Entry<Integer, String>> byKey = Entry.comparingByKey();
        Comparator<Entry<Integer, String>> byValue = Entry.comparingByValue();

        System.out.println("Sorted by Key: ");
        gameShows.entrySet()
                .stream()
                .sorted(byKey)
                .forEach(System.out::println);
        System.out.println();

        System.out.println("Sorted by Value: ");
        gameShows.entrySet()
                .stream()
                .sorted(byValue)
                .forEach(System.out::println);

        System.out.println();






    }
}
