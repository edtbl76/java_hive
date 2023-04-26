package com.oop.instanceOfExample;

import java.util.LinkedList;
import java.util.List;

public class Example3_typecastChecks {

    public static void main(String[] args) {

        List<Integer> list = List.of(1, 2, 3);
        if (list instanceof LinkedList) {
            LinkedList<Integer> linkedList = (LinkedList<Integer>) list;
            System.out.println("This never gets printed!");
        } else {
            System.out.println(list);   // This does!
        }
    }
}
