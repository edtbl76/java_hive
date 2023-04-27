package javaeight.streamapi;

import utils.Generated;

import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.List;

@Generated
@SuppressWarnings("java:S106")
public class DemoCreation {

    /*
        Object Streams:

            Stream<T>

        Primitive Streams:

            IntStream, DoubleStream, LongStream

        Operations Types:

        1. Intermediate
            - Params = Functional Interfaces
            - Return = new stream.

            EX: map(), filter(), reduce() etc.

        2. Terminal (produce operations)

            - filtering
            - slicing
            - mapping
            - matching and finding
            - reduction
            - collect
     */

    public static void streamOfExample() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        stream.forEach(System.out::println);
    }

    @SuppressWarnings("java:S125")
    public static void listStreamExample() {
       List<String> list = new ArrayList<>(List.of("a", "b", "c", "d"));

       // this can be simplified just to list.forEach();
       Stream<String> stream = list.stream();
       stream.forEach(System.out::println);
    }

    public static void main(String[] args) {

        // 1. Stream.of()
        streamOfExample();

        // 2. List.stream()
        listStreamExample();

    }
}
