package CollectionsInJava.HashMap.Methods;

import CollectionsInJava.HashMap.HashMapMaker;

import java.util.HashMap;

public class HM_get {

    public static void main(String[] args) {
        HashMap<Integer, String> map = HashMapMaker.getMap();
        map.forEach((integer, s) -> System.out.println(map.get(integer)));
    }
}
