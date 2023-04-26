package com.oop.Constructors;

import java.util.Optional;

public class ConstructorChaining {
    public static void main(String[] args) {
        // These use Constructor Chaining, where each Constructor is build on its predecessor
        System.out.println(new CCTop());
        System.out.println(new CCTop("start"));
        System.out.println(new CCTop("start", "end"));
        System.out.println(new CCTop("", "nostart"));
        System.out.println(new CCTop("", "   "));   // DOUBLE empty returns java.lang.Object.toString()

        //All of these examples use super to reference the base class.
        // The difference is validated by the bookend results which show CCNext as the class as opposed to CCTop
        System.out.println(new CCNext());
        System.out.println(new CCNext("start"));
        System.out.println(new CCNext("start", "end"));
        System.out.println(new CCNext("", "nostart"));
        System.out.println(new CCNext("", "   "));   // DOUBLE empty returns java.lang.Object.toString()


    }
}

class CCTop {

    private String first;
    private String last;

    public CCTop() {}

    public CCTop(String first) {
        this(); // calls default constructor
        this.first = first;
    }

    public CCTop(String first, String last) {
        this(first);    // calls previous constructor.
        this.last = last;
    }


    @Override
    public String toString() {
        /*
            adding the additional blank ensures that we are checking for ""
         */
        if ((Optional.ofNullable(this.first).isEmpty() || this.first.isBlank()) &&
                (Optional.ofNullable(this.last).isEmpty() || this.last.isBlank()))
            return super.toString();
        else if (Optional.ofNullable(this.last).isEmpty() || this.last.isBlank())
            return this.first;
        else if (Optional.ofNullable(this.first).isEmpty() || this.first.isBlank())
            return this.last;
        else
            return this.first + " " + this.last;
    }
}

class CCNext extends CCTop {

    public CCNext() {
        super();
    }

    public CCNext(String first) {
        super(first);
    }

    public CCNext(String first, String last) {
        super(first,  last);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}