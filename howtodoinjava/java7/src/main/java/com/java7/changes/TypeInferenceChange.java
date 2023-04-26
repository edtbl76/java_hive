package com.java7.changes;

import java.util.HashMap;
import java.util.Map;

public class TypeInferenceChange {

    /*
        Before Java7, when using GENERICS (as of 1.5), you had to supply type parameters
        to var types AND their actual types.



            - Blank Diamond (aka the DIAMOND OPERATOR) on RIGHT side of the declaration is supported in Java7
            - Compiler is smart enough in Java 7 to ID that blank diamond on right
            is inferring to type defined on left hand side of declaration statement.
     */
    public static void main(String[] args) {

        Map<Integer, String> weekdays = new HashMap<>();
        weekdays.put(1, "Monday");
        weekdays.put(2, "Tuesday");
        weekdays.put(3, "Wednesday");
        weekdays.put(4, "Thursday");
        weekdays.put(5, "Friday");
        weekdays.put(6, "Saturday");
        weekdays.put(7, "Sunday");

        for(int i = 1; i <= weekdays.size(); i++) {
            System.out.println(weekdays.get(i));
        }
    }
}
