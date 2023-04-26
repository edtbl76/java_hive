package com.java8.forEach;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class forEachMapExample2_CustomAction {

    public static void main(String[] args) {

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        Consumer<Map.Entry<Integer, String>> actionCustom = integerStringEntry -> {
            System.out.println("Key   : " + integerStringEntry.getKey());
            System.out.println("Value : " + integerStringEntry.getValue());
        };

        map.entrySet().forEach(actionCustom);
    }
}
