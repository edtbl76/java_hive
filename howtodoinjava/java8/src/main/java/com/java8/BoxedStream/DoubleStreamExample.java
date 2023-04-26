package com.java8.BoxedStream;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class DoubleStreamExample {

    /*
        boxed stream of doubles -> List<Double>
     */
    public static void main(String[] args) {

        List<Double> doubles = DoubleStream.of(1.,2.,3.,4.,5.)
                .boxed()
                .collect(Collectors.toList());

        System.out.println(doubles);

        Optional<Double> min = DoubleStream.of(1.,2.,3.,4.,5.)
                .boxed()
                .min(Double::compareTo);

        System.out.println(min.get());

    }
}
