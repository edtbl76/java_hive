package com.basics.Operators;


public class PrePostIncrementDecrementExample {

    public static void main(String[] args) {

        int i = 1;
        System.out.println("Start                   : " + i);        // 1

        /*
        Pre Increment
            - increments, then evalutes
         */
        System.out.println("Pre Increment In Print  : " + ++i);      // 2
        System.out.println("Pre Increment Result    : " + i);        // 2

        /*
        Post Increment
            - evalutes (to the original value) and then increments.
         */
        System.out.println("Post Increment In Print : " + i++);     // 2
        System.out.println("Post Increment Result   : " + i);       // 3

        /*
            PRE:
                - in the cases of pre incr/decr, the work is done immediately
                    - there is NO difference between assigning the result to i or ++i, because the values are the same.

            POST:
                - in the cases of post incr/decr, the original value is retained during the operation
                    - this results in a difference
                        - assigning to i++ will get me the "old" value
                        - assigning to i, after the operation, will result in the new value.
         */



    }

}
