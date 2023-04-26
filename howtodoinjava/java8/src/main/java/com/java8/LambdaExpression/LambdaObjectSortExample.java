package com.java8.LambdaExpression;

import java.util.Arrays;

public class LambdaObjectSortExample {

    public static void main(String[] args) {
        Employee[] employees = {
                new Employee("Optimus Prime"),
                new Employee("Bumblebee"),
                new Employee("Grimlok"),
                new Employee("Jazz")
        };

        System.out.println("Before: " + Arrays.toString(employees));
        Arrays.sort(employees, Employee::nameCompare);
        System.out.println("After : " + Arrays.toString(employees));
    }
}
