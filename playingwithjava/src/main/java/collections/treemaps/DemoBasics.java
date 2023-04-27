package collections.treemaps;

import utils.Generated;

import java.util.*;

import static java.util.Comparator.reverseOrder;


/*
    Tree Maps
    1. sorted in the natural ordering of the keys
        - requires that the objects being stored:
            implement the Comparable interface
            OR
            a Comparator is passed to the TreeMap when it is constructed.

    2. does NOT allow null keys (but null values are ok)
    3. NOT thread-safe.
 */
@Generated
@SuppressWarnings({"java:S106", "java:S125", "java:S1192"})
public class DemoBasics {

    @SuppressWarnings({"unused", "MismatchedQueryAndUpdateOfCollection"})
    public static void main(String[] args) {

        /*
         *  Creation
         */

        //1. No Args constructor
        // NOTE: the keys (String in this case) MUST IMPLEMENT Comparable
        Map<String, Integer> mapNoArgs = new TreeMap<>();

        //2. Providing a comparator as an argument
        Map<String, Integer> mapWithComparator = new TreeMap<>(reverseOrder());

        //3. copy constructor from another map
        // NOTE: providing an unsorted map slows down the process, because it has to be sorted.
        Map<String, Integer> mapFromUnsorted = new TreeMap<>(new HashMap<>());

        //4. copy constructor from a sorted map
        Map<String, Integer> mapFromSorted = new TreeMap<>(mapWithComparator);

        Map<String, Integer> poundForPound = new TreeMap<>(
                Map.of(
                        "Jones", 1,
                        "Volkanovski", 2,
                        "Makhachev", 3,
                        "Edwards", 4,
                        "Usman", 5,
                        "Adesanya", 6,
                        "Pereira", 7,
                        "Sterling", 8,
                        "Oliveira", 0
                ));

        System.out.println("Initial Map: " + poundForPound);
        /*
         * Insertion
         */
        //1. put() works like a HashMap. updates if it's there, adds if not.
        poundForPound.put("Oliveira", 9);
        System.out.println("put() - update - " + poundForPound);

        poundForPound.put("Moreno", 10);
        System.out.println("put() - insert- " + poundForPound);

        //2. putAll() adds data from another map.
        Map<String, Integer> nextFive = new TreeMap<>(Map.of(
                "Prochazka", 11,
                "Poirier", 12,
                "Hill", 13,
                "Whittaker", 14,
                "Holloway", 15
        ));
        poundForPound.putAll(nextFive);
        System.out.println("putAll() - " + poundForPound);

        /*
         *  Fetching
         */

        //1. get(Object) - returns null if not present, otherwise returns the value
        System.out.println("Where's Conor? - " + poundForPound.get("McGregor"));
        System.out.println("Where is Jones ranked? - " + poundForPound.get("Jones"));

        //2. getting smallest Key [ Throws NoSuchElementException for an empty Map ]
        TreeMap<String, Integer> ranked = new TreeMap<>(poundForPound);
        System.out.println("First Key: " + ranked.firstKey());

        //3. getting largest Key [ Throws NoSuchElementException for an empty Map ]
        System.out.println("Last Key: " + ranked.lastKey());

        /*
         *  Removal. Returns value of key being removed or 'null' if not present.
         */
        System.out.println("Removing Khabib - " + poundForPound.remove("Khabib"));
        System.out.println("Jones gets busted again - " + poundForPound.remove("Jones"));


        /*
         *  Updating values
         */
        //1. replace(Key, Value) | No Safety Check. Returns old value or null;
        System.out.println("Updating Jones - " + poundForPound.replace("Jones", 1));
        poundForPound.forEach((k, v) -> poundForPound.replace(k, v - 1));
        System.out.println("Updated Ranks w/o Jones: " + poundForPound);

        //2. replace(Key, old, New) | Safety Check. True if value is replaced, old if not.
        System.out.println("Replacing Cormier - Not Present - "
                + poundForPound.replace("Cormier", 1, 2));
        System.out.println("Replacing Volkanovski - value incorrect - "
                + poundForPound.replace("Volkanovski", 2, 1));
        System.out.println("Hill loses! - value correct - "
                + poundForPound.replace("Hill", 12, 15));



    }



}
