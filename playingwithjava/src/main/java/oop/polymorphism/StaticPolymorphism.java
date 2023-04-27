package oop.polymorphism;

import utils.Generated;

public class StaticPolymorphism {

    @Generated
    private StaticPolymorphism() {
        throw new IllegalStateException("Utility Class");
    }

    static int add(int n1, int n2) {
        return n1 + n2;
    }

    static int add(int n1, int n2, int n3) {
        return add(n1, n2) + n3;
    }

    @SuppressWarnings("all")
    static int add(int n1, int n2, int n3, int n4) {
        return add(n1, n2, n3) + n4;
    }

}
