package com.oop.instanceOfExample;

public class Example2_withArrays {

    public static void main(String[] args) {

        // primitives
        int[] p1 = new int[3];
        float[] p2 = new float[3];

        System.out.println(p1 instanceof Object);
        System.out.println(p1 instanceof int[]);
        System.out.println(p2 instanceof Object);
        System.out.println(p2 instanceof float[]);

        // Object arrays
        Integer[] o1 = new Integer[3];
        Float[] o2 = new Float[3];
        String[] o3 = new String[3];

        System.out.println(o1 instanceof Object);
        System.out.println(o1 instanceof Object[]);
        System.out.println(o1 instanceof Integer[]);
        System.out.println(o1 instanceof Number[]);
        System.out.println(o2 instanceof Object);
        System.out.println(o2 instanceof Object[]);
        System.out.println(o2 instanceof Float[]);
        System.out.println(o2 instanceof Number[]);
        System.out.println(o3 instanceof Object);
        System.out.println(o3 instanceof Object[]);
        System.out.println(o3 instanceof String[]);

    }
}
