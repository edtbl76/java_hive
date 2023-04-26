package com.java8.stream;

import java.util.Date;
import java.util.stream.Stream;

public class StreamGenerateExample {

    public static void main(String[] args) {
        Stream<Date> stream = Stream.generate(Date::new);

        // Lambda version of this
        // Stream.generate(() -> { return new Date(); });
        stream.forEach(System.out::println);
    }
}
