package javaeight.streamapi;

import utils.Generated;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Generated
@SuppressWarnings("java:S106")
public class DemoLazyEvaluation {

    static List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

    public static void main(String[] args) {

        /*
            Print statements have been added to demonstrate HOW this is evaluated.

            // this demonstrates fail fast/lazy eval.
            - There is no need to evaluate the second filter if the first fails.
         */
        Optional<Integer> number = data.stream()
                .filter(integer -> {
                    System.out.println("Processing first filter for number " + integer);
                    return integer > 5;
                })
                .filter(integer -> {
                    System.out.println("Processing second filter for number " + integer);
                    return integer % 3 == 0;
                })
                .findFirst();
        number.ifPresent(System.out::println);

    }
}
