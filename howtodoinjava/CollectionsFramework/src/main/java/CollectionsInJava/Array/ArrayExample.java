package CollectionsInJava.Array;

import java.util.stream.IntStream;

public class ArrayExample {

    public static void main(String[] args) {
        int[] a = new int[5];

        IntStream.range(0, a.length).forEach(value -> a[value] = (int)Math.pow(2, value));

        IntStream.range(0, a.length).forEach(value -> System.out.println("Index: " + value + " Value: " + a[value]));
    }
}
