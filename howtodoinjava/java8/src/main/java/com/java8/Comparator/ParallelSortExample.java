package com.java8.Comparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ParallelSortExample {

    /*
        Parallel Sorting is a way to use multiple threads to sort a collection of objects in parallel.
        - This is especially useful for those scenarios where we have MANY MANY MANY MANY objects to sort.

        NOTE: for SMALL collections of objects normal sorting is good enough AND RECOMMENDED. (i.e. the overhead
        of creating/managing the threads isn't worth the trouble or TIME even if the language and library is doing the
        work for you)
     */
    public static void main(String[] args) {
        List<ExampleEmployee> employees = ComparatorExample.getEmployees();
        Comparator<ExampleEmployee> groupByComparator = Comparator.comparing(ExampleEmployee::getFirstName)
                .thenComparing(ExampleEmployee::getLastName);

        // Parallel Sorting
        /*
            This kind of sucks, because you have to convert this to an array of Objects before you can perform the
            parallel sort.
         */
        ExampleEmployee[] employeesArray = employees.toArray(new ExampleEmployee[0]);
        Arrays.parallelSort(employeesArray, groupByComparator);
        for(int i = 0; i < employees.size(); i++) {
            System.out.println(employeesArray[i]);
        }

    }
}
