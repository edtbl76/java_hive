package com.java8.Comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



public class ComparatorExample {

    static List<ExampleEmployee> getEmployees() {
        List<ExampleEmployee> employees = new ArrayList<>();
        employees.add(new ExampleEmployee(6, "Yash", "Chopra", 25));
        employees.add(new ExampleEmployee(2, "Aman", "Sharma", 28));
        employees.add(new ExampleEmployee(3, "Aakash", "Yaadav", 52));
        employees.add(new ExampleEmployee(5, "David", "Kameron", 19));
        employees.add(new ExampleEmployee(4, "James", "Hedge", 72));
        employees.add(new ExampleEmployee(8, "Balaji", "Subbu", 88));
        employees.add(new ExampleEmployee(7, "Karan", "Johar", 59));
        employees.add(new ExampleEmployee(1, "Lokesh", "Gupta", 32));
        employees.add(new ExampleEmployee(9, "Vishu", "Bissi", 33));
        employees.add(new ExampleEmployee(10, "Lokesh", "Ramachandran", 60));
        return employees;

    }

    public static void main(String[] args) {
        List<ExampleEmployee> employees = getEmployees();

        // Print Initial
        System.out.println("Initial: ");
        employees.forEach(System.out::println);

        // Sort'em by first name and do it again
        employees.sort(Comparator.comparing(ExampleEmployee::getFirstName));
        System.out.println("\nSorted (First Name): ");
        employees.forEach(System.out::println);

        // Sort'em by first name (reversed)
        // Instead of explicitly defining the comparator as an argument to employees.sort(), we are creating a
        // comparator (that  might be used several times) and then passing that variable (in reverse)  to employees.sort()
        Comparator<ExampleEmployee> comparator = Comparator.comparing(ExampleEmployee::getFirstName);
        employees.sort(comparator.reversed());
        System.out.println("\nReverse Sorted (First Name): ");
        employees.forEach(System.out::println);

        // Sort 'em by last name  (changing the comparator to last name and passing it as-is to sort()
        comparator = Comparator.comparing(ExampleEmployee::getLastName);
        employees.sort(comparator);
        System.out.println("\nSorted (Last Name): ");
        employees.forEach(System.out::println);



    }
}
