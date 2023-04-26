package CollectionsInJava.HashMap.Methods;

import CollectionsInJava.HashMap.HashMapMaker;

import java.util.HashMap;

public class HM_contains {

    public static void main(String[] args) {
        HashMap<Integer, String> map = HashMapMaker.getMap();
        System.out.println(map);

        System.out.println(map.containsKey(1));
        System.out.println(map.containsKey(9));

        System.out.println(map.containsValue("one"));
        System.out.println(map.containsValue("nine"));

    }
}
