package com.java8.ExactArithmetic;

public class nextDownExample {

    /*
        nextDown METHOD
            -  this is a helpful tool when you are faced with a situation where you want to return a number less than n,
            - but the number that was returned for computing happened to be N
     */
    public static void main(String[] args) {

        // nextUp has been around since 1.6, which gives us the next HIGHER value.
        System.out.println(Math.nextUp(100));
        System.out.println(Math.nextUp(100.999));

        // 1.8 brings us nextDown!
        System.out.println(Math.nextDown(100));
        System.out.println(Math.nextDown(100.999));
    }
}
