package CollectionsInJava.HashMap.Methods;

import CollectionsInJava.HashMap.HashMapMaker;

import java.util.HashMap;

public class HM_puts {
    public static void main(String[] args) {
        HashMap<Integer, String> map = HashMapMaker.getMap();
        System.out.println(map);
        map.put(6, "six");
        System.out.println(map);

        HashMap<Integer, String> newMap = new HashMap<>();
        newMap.putAll(map);
        System.out.println(newMap);
    }
}
