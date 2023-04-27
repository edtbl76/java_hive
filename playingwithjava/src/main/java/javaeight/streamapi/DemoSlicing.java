package javaeight.streamapi;

import utils.Generated;

import java.util.ArrayList;
import java.util.List;

@Generated
@SuppressWarnings({"java:S106", "java:S1192"})
public class DemoSlicing {

    static List<String> countries = new ArrayList<>(List.of(
            "USA", "Denmark", "Spain", "Canada", "USA", "Germany", "Mexico", "Canada", "Canada",
            "Mexico", "Denmark"));


    /*
        slicing operations
            - intermediate operations used to slice streams
     */

    public static void main(String[] args) {

        /*
            1. distinct() - outputs only the distinct/unique elements.
                       - for custom objects, equals/hashCode must be implemented
                       - useful for de-duplication.

         */
        countries.stream()
                .distinct()
                .map(s -> s + " ")
                .forEach(System.out::print);
        System.out.println();


        /*
            2. limit(long maxSize)
                - returns a stream consisting of the elements of the stream, truncated
                to be no longer than the maxSize in length.
         */
        countries.stream()
                .distinct()
                .limit(3)
                .map(s -> s + " ")
                .forEach(System.out::print);
        System.out.println();

        /*
            3. skip(long n)
            - returns a stream consisting of the remaining elements of a string after discarding
            - the first n elements.

            - if the stream contains less than n elements, an empty stream is returned
         */
        countries.stream()
                .distinct()
                .skip(2)
                .map(s -> s + " ")
                .forEach(System.out::print);
        System.out.println();



    }

}

