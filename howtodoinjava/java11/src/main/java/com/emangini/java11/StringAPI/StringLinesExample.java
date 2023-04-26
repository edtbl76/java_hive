package com.emangini.java11.StringAPI;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;

public class StringLinesExample {

    public static void main(String[] args) {

        String testString = "hello\nworld\nis\nexecuted";
        System.out.println("Original : " + testString);

        List<String> lines = new ArrayList<>();
        testString.lines().forEach(lines::add);

        Assert.assertEquals(List.of("hello","world","is","executed"), lines);



    }
}
