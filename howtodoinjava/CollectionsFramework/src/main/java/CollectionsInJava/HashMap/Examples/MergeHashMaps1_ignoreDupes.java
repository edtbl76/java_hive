package CollectionsInJava.HashMap.Examples;

import java.util.HashMap;

public class MergeHashMaps1_ignoreDupes {
    public static void main(String[] args) {

        // map1
        HashMap<Integer, String> map1 = new HashMap<>();
        map1.put(1, "A");
        map1.put(2, "B");
        map1.put(3, "C");
        map1.put(5, "E");

        // map2
        HashMap<Integer, String> map2 = new HashMap<>();
        map2.put(1, "G");
        map2.put(2, "B");
        map2.put(3, "C");
        map2.put(4, "D");

        System.out.println("Map1: " + map1);
        System.out.println("Map2: " + map2);

        // Merge
        map1.putAll(map2);
        System.out.println("Merged: " + map1);

    }
}
