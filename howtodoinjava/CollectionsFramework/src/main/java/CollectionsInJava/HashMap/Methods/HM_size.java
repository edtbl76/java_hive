package CollectionsInJava.HashMap.Methods;

import CollectionsInJava.HashMap.HashMapMaker;

import java.util.HashMap;

public class HM_size {
    public static void main(String[] args) {
        HashMap<Integer, String> map = HashMapMaker.getMap();
        System.out.println(map.size());
    }
}
