package com.emangini.ContractBetweenEqualsAndHashCode;

public class EqualsTest_DefaultBehavior {
    public static void main(String[] args) {

        Employee e1 = new Employee();
        Employee e2 = new Employee();

        e1.setId(100);
        e2.setId(100);

        // This prints false, because despite the fact that the values are the same, they don't have the
        // same memory location.
        System.out.println(e1.equals(e2));
    }
}
