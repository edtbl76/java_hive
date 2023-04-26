package com.emangini.java9.ImmutableCollections;

import java.util.List;

public class ImmutableListExample {

    /*
        List.of()
            - static factory methods that create immutable lists.

            static <E> List<E> of()
            static <E> List<E> of(E e1, E, e2, .....etc)

        RULES
            - THEY ARE F'N IMMUTABLE
                - elements can't be added, removed or replaced
                - calling mutators throws UnsupportedOperationException
                    - (add, addAll, clear, remove, removeAll, ReplaceAll)
            - NULL elements aren't supported
                - throws NPE
            - they are SERIALIZABLE, if and only if, ALL elements are SERIALIZABLE
            - Order of elements in the list is
                - same as the order of the provided arguments
                - OR
                - same as the order of elements in the provided array
     */
    public static void main(String[] args) {
        List<String> names = List.of("BamKam", "Big Mouth", "ET3", "Number4");

        // Perserve elements order
        System.out.println(names);

        try {
            names.add("Jeremy Lane");
        } catch (UnsupportedOperationException e) {
            System.err.println(e.toString());
        }

        try {
            List<String> names2 = List.of(null);
        } catch (NullPointerException npe) {
            System.err.println(npe.toString());
        }
    }
}
