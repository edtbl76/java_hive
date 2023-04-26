package com.javaoop.ImmutableClass;

import java.util.Date;

public class TestImmutableClassExample {

    public static void main(String[] args) {
        ImmutableClassExample ice  = ImmutableClassExample.createNewInstance(100, "test", new Date());
        System.out.println(ice);

        tryModification(ice.getImmutableField1(), ice.getImmutableField2(), ice.getMutableField());
        System.out.println(ice);    // values don't change... so we know that the class is immutable (and resilient)(
    }

    private static void tryModification(Integer if1, String if2, Date mf) {
        if1  = 10000;
        if2 = "test changed";
        mf.setDate(10);
    }
}
