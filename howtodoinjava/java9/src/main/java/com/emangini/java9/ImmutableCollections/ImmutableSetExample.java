package com.emangini.java9.ImmutableCollections;

import java.util.List;
import java.util.Set;

public class ImmutableSetExample {

    // Check out Immutable ListExample.
    /*
        Same rules apply AND:
            - Set doesn't allow duplicate elements (IllegalArgumentException)
            - iteration order of SET ELEMENTS is unspecified and subject to change
     */
    public static void main(String[] args) {
        Set<String> names = Set.of("Carson", "Penny", "Prosise");

        // Element ordering not fixed.
        System.out.println(names);

        // Mutator fails
        try {
            names.add("Homer");
        } catch (UnsupportedOperationException ex) {
            System.err.println(ex.toString());
        }

        // NPE
        try {
            Set<String> names2 = Set.of(null);
        } catch (NullPointerException npe) {
            System.err.println(npe.toString());
        }

        // Duplicate element
        try {
            Set<String> twins = Set.of("Griffin", "Griffin");
        } catch (IllegalArgumentException ex) {
            System.err.println(ex.toString());
        }
    }
}
