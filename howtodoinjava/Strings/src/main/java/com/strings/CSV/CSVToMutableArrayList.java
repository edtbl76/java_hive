package com.strings.CSV;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVToMutableArrayList {

    public static void main(String[] args) {

        String alpha = "comma, separated, values";
        String regex = "\\*,\\*";

        // Typecast to ArrayListt
        List<String> result = new ArrayList<>(Arrays.asList(alpha.split(regex)));
        System.out.println(result);
        result.add("dude");        // Mutable! I change it!
        System.out.println(result);
    }
}

