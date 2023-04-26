package com.emangini.java9.StreamAPIUpdates;

import java.util.stream.Stream;

public class StreamOfNullable {

    /*
        Java8
            - null value in a stream results in NPE... pandemonium ensues.
        Java9
            - ofNullable allows us to create a SINGLE-ELEMENT stream which wraps a value if not null, or is otherwise an
            empty stream.

            (Similar to RxJava Maybe)
     */
    public static void main(String[] args) {

        /*
        In the following example, count() returns the number of non-empty elements in the stream.
         */
        Stream<String> stream = Stream.ofNullable("123");
        System.out.println(stream.count());

        stream = Stream.ofNullable(null);   //  awwww shit.
        System.out.println(stream.count());
    }
}
