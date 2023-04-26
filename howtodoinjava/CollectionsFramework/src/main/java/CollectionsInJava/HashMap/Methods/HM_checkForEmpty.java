package CollectionsInJava.HashMap.Methods;

import CollectionsInJava.HashMap.HashMapMaker;

import java.util.HashMap;

public class HM_checkForEmpty {
    public static void main(String[] args) {
        HashMap<Integer, String> map = HashMapMaker.getMap();
        if(!map.isEmpty()) {
            System.out.println(map);
        }
    }
}
