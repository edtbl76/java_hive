package CollectionsInJava.HashMap.Methods;

import CollectionsInJava.HashMap.HashMapMaker;

import java.util.HashMap;

public class HM_keysAndValues {
    public static void main(String[] args) {
        HashMap<Integer, String> map = HashMapMaker.getMap();
        System.out.println("Map: " + map);
        System.out.println("KeySet: " + map.keySet());
        System.out.println("Values: " + map.values());
    }
}
