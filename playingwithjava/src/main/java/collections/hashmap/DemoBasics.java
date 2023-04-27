package collections.hashmap;

import utils.Generated;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.*;


/*
    Basic HashMap Features
    - keys must be unique
    - only allows a single null key
    - values can be null or duplicate
    - keys are stored in random order.
 */
@SuppressWarnings({"java:S106", "java:S125", "unused"})
@Generated
public class DemoBasics {

    public static final String THREE = "Three";

    public static void main(String[] args) {

        /*
         *  Creation!
         */

        // 1. No Args [ initial capacity = 16, load factor = 0.75 ]
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        Map<String, Integer> mapNoArgs = new HashMap<>();

        // 2. Initial Capacity Specified (Default Load Factor of 75%)
        Map<String, Integer> mapInitialCapacity = new HashMap<>(32);

        // 3. Initial Cap and LF are provided.
        Map<String, Integer>  mapAllArgs = new HashMap<>(32, 0.5f);

        // 4. copy Constructor
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        Map<String, Integer> copiedMap = new HashMap<>(mapNoArgs);

        /*
         *  Insertion
         */
        //noinspection
        Map<String, Integer> map = new HashMap<>(Map.of("One", 1, "Two", 2, THREE, 3));
        System.out.println("Initial Map: " + map);

        // 1. put() case 1: if key doesn't exist
        map.put("Four", 40);
        System.out.println("put() [create] " + map);

        // 2. put() case 2: key exists:
        /*
            This is a destructive operation, but it's faster than replace() which checks
            the old value.
         */
        map.put("Four", 4);
        System.out.println("put() [update] " + map);

        // 3. putIfAbsent(): [wont update]
        map.putIfAbsent("Four", 4000);
        System.out.println("putIfAbsent [won't update] " + map);

        // 4. putIfAbsent(): normal operation
        map.putIfAbsent("Five" , 5);
        System.out.println("putIfAbsent [create] " + map);

        // 5. putAll(): Add another collection
        Map<String, Integer> moreNumbers = new HashMap<>(Map.of("Six", 6, "Seven", 7, "Eight", 8));
        map.putAll(moreNumbers);
        System.out.println("putAll() " + map);


        /*
         *  Fetch/ Retrieval
         */
        // 1. get()
        System.out.println("get(Object key) 'One':" + map.get("One"));

        // 2. get() If doesn't exist, returns null
        System.out.println("get(Object key) 'Nine':" + map.get("Nine"));

        // 3. getOrDefault(), If exist works normally
        System.out.println("get(Object key) 'Eight':" + map.getOrDefault("Eight", 0));

        // 4. get() If doesn't exist, returns specified default
        System.out.println("get(Object key) 'Nine':" + map.getOrDefault("Nine", 0));


        /*
         *  Updates
         */
        map.put("Nine", 99);
        System.out.println("Old Value for 'Nine' is - " + map.get("Nine"));

        // 1. replace(K key, V old, V new), if old value doesn't match
        System.out.println("replace('Nine', 10, 9) - " + map.replace("Nine", 10, 9));
        System.out.println("Value for 'Nine' is - " + map.get("Nine"));

        // 2. replace(K key, V old, V new), if old value DOES match.
        System.out.println("replace('Nine', 99, 9) - " + map.replace("Nine", 99, 9));
        System.out.println("Value for 'Nine' is - " + map.get("Nine"));

        // 3. replace(K key, V value) - if doesn't exist returns null.
        System.out.println("replace('Ten', 10) - " + map.replace("Ten", 10));

        // 4. replace(K key, V value) - if exist, returns OLD value.
        map.put("Ten", 0);
        System.out.println("replace('Ten', 10') Old Value is " + map.replace("Ten", 10));
        System.out.println("New Value is " + map.get("Ten"));

        // 5. replaceAll, w/ a BiFunction
        // - BiFunction<? super K, ? super V, ? extends V> function
        System.out.println("Before replaceAll() " + map);
        map.replaceAll((k,v) -> v + 10);
        System.out.println("After replaceAll() " + map);

        /*
         *  Removal/ Deletion
         */
        // 1. If key is present, returns removed value.
        System.out.println("remove('Ten') removes value -> " + map.remove("Ten"));

        // 2. If key is NOT present, returns null
        System.out.println("remove('Ten') removes value -> " + map.remove("Ten"));

        // 3. Safe removal, if value isn't present, false
        System.out.println("remove('Nine', 9) - " + map.remove("Nine", 9));

        // 4. Safe removal, if value is present, true
        System.out.println("remove('Nine', 19) - " + map.remove("Nine", 19));


        /*
         *  Operations
         */

        //1. Contains key
        System.out.println("containsKey() 'Ten' - " + map.containsKey("Ten"));
        System.out.println("containsKey() 'One' - " + map.containsKey("One"));

        //2. Contains value
        System.out.println("containsValue() '1' - " + map.containsValue(1));
        System.out.println("containsValue() '11' - " + map.containsValue(11));

        //3. Get all keys
        System.out.println("keySet() - " + map.keySet());
        System.out.println("values() - " + map.values());

        //4. isEmpty()
        System.out.println("isEmpty() - " + map.isEmpty());
        map.clear();
        //noinspection ConstantValue
        System.out.println("isEmpty() after clear - " + map.isEmpty());


        /*
         *  Java 8 Enhancements
         */

        /*
            compute(Key, BiFunction)
                - BiFunction
            - rather than explicitly specifying the new value, compute provides a programmatic
            calculation to update/change the existing value
            - (good for freq. charts, counters, etc..)

            1. if the remapping bifunction returns null, then the mapping is removed (or remains null)
            2. otherwise, the value is updated.
         */
        map = new HashMap<>(Map.of("One", 1, "Two", 2, THREE, 33));
        System.out.println("New Map: " + map);

        map.compute(THREE, (k, v) -> v == null ? 0 : v - 30);
        System.out.println("compute() - " + map);

        map.compute("Four", (k, v) -> v == null ? 0 : v - 30);
        System.out.println("compute() - " + map);

        /*
            2. computeIfAbsent(Key, Function) >-- same as above, but it won't replace a value.
            -- This is a FUNCTION, not a bifunction. This means that the calculation for the value can't
            be based on the previous value associated w/ this key, because it is null.
         */
        // This won't even compile, because the Function requires the value not to exist.
        // map.computeIfAbsent("Four", (k, v) -> v == null ? 0 : v - 30);


        /*
            map.computeIfAbsent("Five", new Function<String, Integer>() {
                @Override
                public Integer apply(String s) {
                    return Collections.max(finalMap.values()) + 1;
                }
            });
         */
        Map<String, Integer> finalMap = map;
        map.computeIfAbsent("Five", s -> max(finalMap.values()) + 1);
        System.out.println("computeIfAbsent() - " + map);

        /*
            computeifPresent() This is the same as compute(), but it won't do anything if the
            value doesn't exist.
         */
        // This is unnecessary, because v must always be false.
        //map.computeIfPresent("Four", (k, v) -> v == null ? 0 :  v + 4);
        map.computeIfPresent("Four", (k, v) -> v + 4);
        map.computeIfPresent("Five", (k, v) -> v + 1);
        System.out.println("computeIfPresent() True - " + map);

        map.computeIfPresent("Six", (k, v) -> v + 1);
        System.out.println("computeIfPresent() False - " + map);

        /*
            merge(K key, V value, BiFunction remappingFunction)

            - key to be merged
            - value to be inserted if key is NOT present
            - Function is used to update the value if key is present.

            Used to merge 2 maps.

         */

        Map<String, Integer> totalPoints = new HashMap<>(
                Map.of(
                   "Tatum", 1959,
                   "Brown", 1552,
                   "Smart", 597,
                   "Horford", 540,
                   "Williams", 236
                ));
        System.out.println("Celtics Totals: " + totalPoints);

        Map<String, Integer> latestGame = new HashMap<>(
                Map.of(
                        "Tatum", 60,
                        "Brown", 50,
                        "Smart", 37,
                        "Horford", 34,
                        "Williams", 21
                )
        );
        System.out.println("Latest Game: (Whoa): " + latestGame);

        latestGame.forEach((k, v) -> totalPoints.merge(k, v, Integer::sum));
        System.out.println("New Totals: " + totalPoints);
    }



}


