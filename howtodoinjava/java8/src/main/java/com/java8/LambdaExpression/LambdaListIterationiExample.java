package com.java8.LambdaExpression;

import java.util.ArrayList;
import java.util.List;

public class LambdaListIterationiExample {

    public static void main(String[] args) {
        List<String> pointList = new ArrayList<>();
        pointList.add("1");
        pointList.add("2");

        pointList.forEach(System.out::println);
    }
}
