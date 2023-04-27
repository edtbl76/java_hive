package javaeight.streamapi;

import utils.Generated;

import java.util.stream.Stream;

@Generated
@SuppressWarnings("java:S106")
public class DemoParallelStream {

    /*
        Use Cases for Parallel Streams
        - We have a LARGE amount of data to process.
        - We are trying to solve a performance problem.
        - All of the shared resources between threads need to be synchronized properly, otherwise it
        might produce unexpected results.

        According to Brian Goetz:

        Consider the following before going for parallelization:
        - Splitting is NOT more expensive than doing the work.
        - Task dispatch or management costs between threads is not too high
        - the result combination cost must not be too high
        - Use the NQ MOdel.

            N X Q > 10000

                N = number of data items.
                Q = amount of work per item.
     */

    public static void main(String[] args) {
        Stream.of(1, 2, 3, 4, 5, 6, 7)
                .forEach(num -> System.out.println(num + " " + Thread.currentThread().getName()));


        System.out.println("-------------------------------- Parallel Stream -----------------------------");
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14)
                .parallel()
                .forEach(num -> System.out.println(num + " " + Thread.currentThread().getName()));
    }
}
