package com.java8.JoinArray;

public class Java8StringJoinExample3 {

    /*
        Similar to 2, but instead of an ArrayList, this is an Array of Strings.
     */
    public static void main(String[] args) {
        String[] strArray = {"W", "A", "S", "P"};
        String joinedString = String.join(".", strArray);
        System.out.println(joinedString);
    }
}
