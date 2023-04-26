package com.emangini.ContractBetweenEqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

public class EqualsTest3_OverrideHashCodeAndEquals {


    public static void main(String[] args) {
        BestEmployee be1 = new BestEmployee();
        BestEmployee be2 = new BestEmployee();

        be1.setId(5);
        be2.setId(5);

        System.out.println(be1.equals(be2));    // still prints True

        Set<BestEmployee> employees = new HashSet<>();
        employees.add(be1);
        employees.add(be2);

        System.out.println(employees);      // This only prints a single object. (HashCodes are now equal)
    }
}
