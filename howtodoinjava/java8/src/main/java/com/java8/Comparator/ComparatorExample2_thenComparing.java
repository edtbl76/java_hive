package com.java8.Comparator;

import java.util.Comparator;
import java.util.List;

public class ComparatorExample2_thenComparing {

    /*
        This example compares against multiple fields.

        This has clear use cases when we are sorting against non-unique fields, but want to ensure additional
        sorting.
        - this is also a good way to narrow down unique fields... i.e.
        if we have a series of unique product serial numbers, we can first sort by model number and then by serial number,
        minimizing the data being returned.
     */
    public static void main(String[] args) {
        List<ExampleEmployee> employees =  ComparatorExample.getEmployees();

        // Create a multi-field comparator
        Comparator<ExampleEmployee> groupByComparator = Comparator
                .comparing(ExampleEmployee::getFirstName)
                .thenComparing(ExampleEmployee::getLastName);

        // pass in the comparator
        employees.sort(groupByComparator);
        employees.forEach(System.out::println);

    }
}
