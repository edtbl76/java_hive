package com.java7.changes;

public class NumberFormattingEnhancement {

    public static void main(String[] args) {

        int betterInt = 10_000_000;
        long betterLong = 10_000_000L;
        float betterFloat = 10_000_000.f;
        double betterDouble = 10_000_000.;

        System.out.println(betterInt);
        System.out.println(betterLong);
        System.out.println(betterFloat);
        System.out.println(betterDouble);

    }
}
