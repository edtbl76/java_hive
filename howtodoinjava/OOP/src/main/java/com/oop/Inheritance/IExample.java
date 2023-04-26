package com.oop.Inheritance;

public class IExample {
    public static void main(String[] args) {
        IManager mgr = new IManager();
        mgr.setId(1L);
        mgr.setFirstName("Ed");
        mgr.setLastName("Mangini");

        System.out.println(mgr);
    }
}
