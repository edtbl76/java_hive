package com.emangini.ContractBetweenEqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

public class EqualsTest2_OverriddenEquals {

    public static void main(String[] args) {
        BetterEmployee be1 = new BetterEmployee();
        BetterEmployee be2 = new BetterEmployee();

        be1.setId(100);
        be2.setId(100);

        System.out.println(be1.equals(be2)); // this prints true!

        Set<BetterEmployee> employees = new HashSet<>();
        employees.add(be1);
        employees.add(be2);
        System.out.println(employees);      // prints 2 objects.
    }
}
