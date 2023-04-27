package collections.hashmap.linkedhashmap;


import utils.Generated;

import java.util.LinkedHashMap;

/*
    - HashMaps don't maintain insertion order.
    - TreeMaps store everything in sorted order.

    LinkedHashMaps retain insertion order
        - important for use cases where sequencing or 'first come, first serve' needs to be
        maintained.

    Features:
    - it doesn't allow duplicate keys
    - can have 1 null key
    - can have multiple null values
    - it is non-synchronized (i.e. not threadsafe!)


   INTERNALS:
   - LinkedHashMaps are identical to HashMaps, but they maintain pointer records for
        the Entry objects that were inserted immediately before and after it.
 */
@Generated
@SuppressWarnings("java:S106")
public class DemoLinkedHashMap {

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "java:S1854", "UnusedAssignment", "ReassignedVariable"})
    public static void main(String[] args) {

        /*
         * Creation
         */
        // 1. No Args constructor (capacity = 16, load factor = 0.75)
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();

        // 2. Specify capacity
        linkedHashMap = new LinkedHashMap<>(32);

        // 3. capacity and loadfactor
        linkedHashMap = new LinkedHashMap<>(32, 0.5f);

        //4. access order flag
        // If this is TRUE, then order is based on last access, if set to FALSE, then it maintains insertion order
        linkedHashMap = new LinkedHashMap<>(32, 0.5f, true);

        /*
         * Insertion (Inherits put() from hashMap)
         */
        linkedHashMap.put("Key", "Value");



    }


}
