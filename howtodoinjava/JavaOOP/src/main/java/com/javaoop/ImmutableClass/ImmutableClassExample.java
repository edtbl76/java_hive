package com.javaoop.ImmutableClass;

import java.util.Date;

/**
 * Always remember that your instance variables will be either MUTABLE or IMMUTABLE
 * Identify them and return new objects with copied content for all MUTABLE objects.
 * Immutable variables can be returned safely without extra effort.
 */
public final class ImmutableClassExample {

    /**
     * Integer class is immutable as it doesn't provide any setter to change its content
     */
    private final Integer immutableField1;

    /**
     * String class is immutable as it doesn't provide any setter to change its content.
     */
    private final String immutableField2;

    /**
     * Date class is mutable as it provides setters to change various date/time parts
     */
    private final Date mutableField;

    // Default private constructor will ensure no unplanned construction of class.
    private ImmutableClassExample(Integer fld1, String fld2, Date date) {
        this.immutableField1 = fld1;
        this.immutableField2 = fld2;
        this.mutableField = new Date(date.getTime());
    }

    // Factory method to store object creation logic in a single place.
    public static ImmutableClassExample createNewInstance(Integer fld1, String fld2, Date date) {
        return new ImmutableClassExample(fld1, fld2, date);
    }

    // Provide no setter methods

    /**
     * Integer class is immutable so we can return the instance variable as it is
     */
    public Integer getImmutableField1() {
        return immutableField1;
    }

    /**
     * String class is immutable so we can return the instance variable as it is
     */
    public String getImmutableField2() {
        return immutableField2;
    }

    /**
     * Date class is mutable so we need some extra care here.
     * We shouldn't return the reference of the original instance variable.
     * Instead, we create a new Date object w/ the content copied into it should be returned.
     */
    public Date getMutableField() {
        return new Date(mutableField.getTime());
    }

    @Override
    public String toString() {
        return immutableField1 + " - " + immutableField2 + " - " + mutableField;
    }
}
