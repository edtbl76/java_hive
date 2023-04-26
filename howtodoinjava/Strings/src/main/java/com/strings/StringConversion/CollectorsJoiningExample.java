package com.strings.StringConversion;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CollectorsJoiningExample {

    public static void main(String[] args) {

        List<String> family = Arrays.asList("Mother", "Father", "Son", "Daughter" ,"Fido");

        /*
            This has the same potential arguments as StringJoiner (See StringJoinerExample)
         */
        String joinedString = family.stream().collect(Collectors.joining(" ", ">>", "<<"));
        System.out.println(joinedString);
    }
}
