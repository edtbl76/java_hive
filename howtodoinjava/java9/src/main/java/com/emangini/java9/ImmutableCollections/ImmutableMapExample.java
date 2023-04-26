package com.emangini.java9.ImmutableCollections;

import java.util.Map;

public class ImmutableMapExample {

    /*
        Check out ImmutableList and ImmutableSet (in that order) first.

       - same operation as List and Set, but the method signatures take K,V
     */
    public static void main(String[] args) {
        Map<String, String> names = Map.ofEntries(
                Map.entry("25", "Sherman"),
                Map.entry("31", "Chancellor"),
                Map.entry("29", "Thomas"));

        System.out.println(names);

        // mutator fail
        try {
            names.put("Number", "Shead");
        } catch (UnsupportedOperationException ex) {
            System.err.println(ex.toString());
        }
    }


}
