package CollectionsInJava.HashMap.Examples;

import java.util.HashMap;

public class MergeHashMaps2_Java8_merge {

    public static void main(String[] args) {

        // map1
        HashMap<Integer, String> map1 = new HashMap<>();

        map1.put(1, "A");
        map1.put(2, "B");
        map1.put(3, "C");
        map1.put(5, "E");
        System.out.println("Map1: " + map1);

        // map2
        HashMap<Integer, String> map2 = new HashMap<>();

        map2.put(1, "G");
        map2.put(2, "B");
        map2.put(3, "C");
        map2.put(4, "D");
        System.out.println("Map2: " + map2);

        // merge 'em
        map2.forEach(
                (key, value) -> map1.merge(key, value, (v1, v2) -> v1.equalsIgnoreCase(v2) ? v1 : v1 + "," + v2)
        );
        System.out.println(map1);



    }
}
