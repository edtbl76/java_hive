package com.emangini.Builders;

import java.util.HashSet;
import java.util.Set;

public class TestCodeExample {

    public static void main(String[] args) {
        BuilderEmployee be1 = new BuilderEmployee();
        BuilderEmployee be2 = new BuilderEmployee();

        be1.setId(13);
        be2.setId(13);

        System.out.println(be1.equals(be2)); //prints true

        Set<BuilderEmployee> employees = new HashSet<>();
        employees.add(be1);
        employees.add(be2);

        System.out.println(employees);  // prints a single object (i.e. because its a Set)
    }
}
