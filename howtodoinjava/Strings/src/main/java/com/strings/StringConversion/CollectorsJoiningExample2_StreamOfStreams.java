package com.strings.StringConversion;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CollectorsJoiningExample2_StreamOfStreams {

    public static void main(String[] args) {
        List<String> parts = Arrays.asList("Cold", "Brew", "Coffee");

        String joined = parts.stream()
                .map(part -> part)
                .collect(Collectors.joining("-", "**", "**"));

        System.out.println(joined);

    }
}
