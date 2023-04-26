package com.oop.Constructors;

import java.lang.reflect.Constructor;
import java.util.Optional;

public class ConstructorOverloading {

    private String firstName;
    private String lastName;

    ConstructorOverloading() {
        // Unnecessary default
    }

    ConstructorOverloading(String firstName) {
        this.firstName = firstName;
    }

    ConstructorOverloading(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {

        /*
            THis is just to demonstrate robust code.
            Maybe this class is tied to a data scraper from web pages that doesn't store all of the information.
            we want first and last names to be valid as null fields.
         */
        Optional<String> fn = Optional.ofNullable(this.firstName);
        Optional<String> ln = Optional.ofNullable(this.lastName);

        if(fn.isEmpty() && ln.isEmpty())
            return super.toString();
        else if (fn.isEmpty())
            return this.lastName;
        else if (ln.isEmpty())
            return this.firstName;
        else
            return this.firstName + " " + lastName;

    }

    public static void main(String[] args) {

        ConstructorOverloading co = new ConstructorOverloading();
        System.out.println("Default: " + co);

        // Don't do this. create a method instead... this is just to demonstrate a behavior.
        co = new ConstructorOverloading("Joe");
        System.out.println("First Name: " + co);

        // Same comment above applies
        co = new ConstructorOverloading("Joe", "Blow");
        System.out.println("Full Name: " + co);

        // this is just for fun.
        co = new ConstructorOverloading("", "LastNameOnly");
        System.out.println("LastName: " + co);
    }
}
