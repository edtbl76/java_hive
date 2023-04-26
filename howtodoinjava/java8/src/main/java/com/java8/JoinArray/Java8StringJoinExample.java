package com.java8.JoinArray;

public class Java8StringJoinExample {

    /*
        String join(CharSequence delimiter, CharSequence... elements)
     */
    public static void main(String[] args) {
        String joinedString = String.join(", ", "Winken", "Blinken", "and Nod");
        System.out.println(joinedString);
    }
}
