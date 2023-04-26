package CollectionsInJava.HashMap.Examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class HashMapComparison {

    public static void main(String[] args) throws CloneNotSupportedException {
        HashMap<Integer, String> map1 = new HashMap<>();
        map1.put(1, "A");
        map1.put(2, "B");
        map1.put(3, "C");

        // map2 is same as map1
        HashMap<Integer, String> map2 = new HashMap<>();
        map2.put(3, "C");
        map2.put(1, "A");
        map2.put(2, "B");

        // map3 is different from  map1
        HashMap<Integer, String> map3 = new HashMap<>();
        map3.put(1, "A");
        map3.put(2, "B");
        map3.put(3, "C");
        map3.put(4, "D");


        // Compare based on entire KV set
        System.out.println(map1.equals(map2));  //  true
        System.out.println(map1.equals(map3));  //  false

        // Comparison based on keysets
        System.out.println(map1.keySet().equals(map2.keySet()));    //  true
        System.out.println(map1.keySet().equals(map3.keySet()));    //  false

        // Comparison based on values (No Dupes Allowed)
        System.out.println(new ArrayList<>(map1.values()).equals(new ArrayList<>(map2.values())));    //  true
        System.out.println(new ArrayList<>(map1.values()).equals(new ArrayList<>(map3.values())));    //  false

        // Comparison based on values (Dupes are allowed)
        System.out.println(new HashSet<>(map1.values()).equals(new HashSet<>(map2.values())));    //  true
        System.out.println(new HashSet<>(map1.values()).equals(new HashSet<>(map3.values())));    //  true

        // Find the extra keys

        /*
            Start my initializing a hashset w/ the keyset of one map
            - add the keyset of the OTHER map  (this will only add differences to the set -- no dupes allowed)
            - then removeAll the original map, leaving behind only the extras/
         */
        HashSet<Integer> unionKeys = new HashSet<>(map1.keySet());
        unionKeys.addAll(map2.keySet());
        unionKeys.removeAll(map1.keySet());
        System.out.println(unionKeys);


    }
}
