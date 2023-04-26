package CollectionsInJava.ArrayList.Methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Spliterator;

public class SpliteratorTryAdvanceExample {

    public static void main(String[] args) {
        ArrayList<Integer> digits = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        Spliterator<Integer> split = digits.spliterator();

        // tryAdvance() is like executing iterator.next() once at a time.. however
        split.tryAdvance(System.out::println);  // prints 1
        split.tryAdvance(System.out::println);  // prints 2
        split.tryAdvance(System.out::println);  // prints 3
        split.tryAdvance(System.out::println);  // prints 4
        split.tryAdvance(System.out::println);  // prints 5
        split.tryAdvance(System.out::println);  // prints 6
        split.tryAdvance(System.out::println);  // does nothing
        split.tryAdvance(System.out::println);  // does nothing


    }
}
