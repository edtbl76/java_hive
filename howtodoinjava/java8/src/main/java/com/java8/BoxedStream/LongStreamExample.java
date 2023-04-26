package com.java8.BoxedStream;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class LongStreamExample {

    /*
        boxed stream of longs -> List<Long>
     */
    public static void main(String[] args) {

        List<Long> longs = LongStream.of(1L, 2L, 3L, 4L, 5L)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(longs);

        Optional<Long> max = LongStream.of(1L, 2L, 3L, 4L, 5L)
                .boxed()
                .max(Long::compareTo);

        System.out.println(max.get());

    }
}
