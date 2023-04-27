package collections.hashmap.enummap;

import utils.Generated;

import java.time.DayOfWeek;
import java.util.EnumMap;

import static java.time.DayOfWeek.MONDAY;

@Generated
@SuppressWarnings("java:S106")
public class Demo {

    /*
        EnumMap
        - keys must be Enums.

        - no null keys, null values are ok
        - keys stored in 'natural order' (this is the order in which the enum constants are declared inside the
                                            enum type)
        - not synchronized / thread safe
        - Iterators are inconsistent
            - don't throw ConcurrentModificationException
            - may/may not show effects of mods in the map that occur when iteration is in progress

        - O(1) for basic operations (get/put)
     */
    public static void main(String[] args) {

        /*
         * Creation
         */
        // 1. Constructor takes the key type as input.
        EnumMap<DayOfWeek, Integer> enumMap = new EnumMap<>(DayOfWeek.class);

        // 2. Constructor takes another EnumMap
        EnumMap<DayOfWeek, Integer> weekdays = new EnumMap<>(enumMap);

        // Insertion
        weekdays.put(MONDAY, 13);

        // Fetch
        System.out.println(weekdays.get(MONDAY));

        // Remove
        weekdays.remove(MONDAY);

    }
}
