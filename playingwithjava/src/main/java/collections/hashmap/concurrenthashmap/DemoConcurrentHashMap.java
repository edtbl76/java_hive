package collections.hashmap.concurrenthashmap;


import utils.Generated;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Generated
@SuppressWarnings({"java:S106", "java:S1854", "java:S125"})
public class DemoConcurrentHashMap {

    /*
        - This is an alternative to Collections.synchronizedMap();

        1. SynchronizedMap() locks the entire map for every read/write operation.
            - ConcurrentHashMap only locks a segment of the map.
            - increases performance allowing different threads to work in different areas of
            the map.

        2. SynchronizedMap() returns an Iterator (fails fast on concurrent modification)
            - ConcurrentHashMap doesn't throw ConcurrentModificationException if one thread tries to modify it
            while another is iterating over it.

        3. SynchronizedMap() allows 1 null key, but CHP allows NO null keys OR values.

    */
    @SuppressWarnings("unused")
    public static void main(String[] args) {

        /*
         * Creation
         */
        // 1. No Args constructor (capacity = 16, load factor = 0.75)
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();

        // 2. Specify capacity and load factor
        ConcurrentHashMap<String, String> hashMapCapacity = new ConcurrentHashMap<>(32);
        ConcurrentHashMap<String, String> hashMapCapAndLF = new ConcurrentHashMap<>(32, 1f);

        //3. Another map
        ConcurrentHashMap<String, String> copied = new ConcurrentHashMap<>(concurrentHashMap);

        System.out.println("Empty Map: " + copied);

        /*
         * Insertion | Works the same as HashMap
         */
        // 1 put()
        copied.put("Geno", "Smith");
        System.out.println("put() - absent - " + copied);

        copied.put("Geno", "Ge-no!");
        System.out.println("put() - exists - " + copied);

        //2. putIfAbsent()
        copied.putIfAbsent("Kenny", "Walker");
        System.out.println("putIfAbsent() - absent - " + copied);

        copied.putIfAbsent("Kenny", "RUNRUNRUNRUN!");
        System.out.println("putIfAbsent() - exists - " + copied);

        //3. putAll() - replaces AND adds
        copied.putAll(Map.of(
                "Geno", "Smith",
                "Tyler", "Lockett",
                "D.K.", "Metcalf"
        ));
        System.out.println("putAll() - absent and exists - " + copied);








    }


}
