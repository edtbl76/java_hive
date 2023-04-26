package com.basics.Variables;

public class VarWideningExample {

    public static void main(String[] args) {

        // Integer is being widened to a long
        int i = 10;
        long j = i;

        System.out.println(i);
        System.out.println(j);
    }
}
