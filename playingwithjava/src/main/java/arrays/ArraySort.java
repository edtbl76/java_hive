package arrays;

import utils.Generated;

import java.util.Arrays;

@Generated
@SuppressWarnings("java:S106")
public class ArraySort {

    /*
        Arrays has 2 sort methods
        - for primitives, the algorithm is quicksort
            - not stable, because relative order isn't maintained
            - we don't care, because we can't differentiate between primitives of equal value

        - for objects, the algorithm is merge sort.
            - Objects CAN be differentiated, so we need to maintain relative order .
     */
    public static void main(String[] args) {

        Integer[] numbers = { 10, 2, 32, 12, 15, 76, 17, 48, 79, 9};
        System.out.println("Initial Array: " + Arrays.toString(numbers));


        // 1. Basic Sort
        System.out.print("sort() : ");
        display(numbers);


        // 2. Sort a subset
        numbers = new Integer[]{10, 2, 32, 12, 15, 76, 17, 48, 79, 9};
        System.out.print("sort(array, fromIndex, toindex): ");
        Arrays.sort(numbers, 3, numbers.length - 3);
        display(numbers);

        //3. parallelSort()
        /*
            Java 8 - Perf Optimization
            - uses a parallel sort-merge algo
            - breaks array into sub-arrays that are sorted and then merged.

                - Uses ForkJoin Pool

             If array is <= 8192 OR Processor is single cored == sequential dual pivot Quicksort
             ELSE                                             == parallel sort
         */
        numbers = new Integer[]{10, 2, 32, 12, 15, 76, 17, 48, 79, 9};
        System.out.print("parallelSort() : ");
        Arrays.parallelSort(numbers);
        display(numbers);

    }

    private static void display(Integer[] integers) {
        Arrays.stream(integers)
                .toList()
                .forEach(integer -> System.out.print(integer + " "));
        System.out.println();
    }
}
