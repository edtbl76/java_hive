package com.oop.instanceOfExample;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Example1 {

    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<>();
        // true
        System.out.println(arrayList instanceof ArrayList);
        System.out.println(arrayList instanceof AbstractList);
        System.out.println(arrayList instanceof List);
        System.out.println(arrayList instanceof Collection);

        //false
        System.out.println(null instanceof ArrayList);
    }
}
