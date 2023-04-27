package javaeight.streamapi;

import javaeight.streamapi.methodreferences.utils.Player;
import utils.Generated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.naturalOrder;
import static javaeight.streamapi.methodreferences.utils.Position.*;
import static javaeight.streamapi.methodreferences.utils.Position.DEFENSIVE_BACK;

@Generated
public class DemoReduction {


    static List<Player> seahawks= new ArrayList<>(List.of(
            new Player("Geno Smith", 1_200_000, QUARTERBACK),
            new Player("Kenneth Walker", 1_100_000, RUNNING_BACK),
            new Player("Tyler Lockett", 9_700_000, WIDE_RECEIVER),
            new Player("D.K. Metcalf", 2_220_000, WIDE_RECEIVER),
            new Player("Noah Fant", 6_850_000, TIGHT_END),
            new Player("Will Dissly", 5_640_000, TIGHT_END),
            new Player("Damien Lewis", 3_000_000, OFFENSIVE_LINE),
            new Player("Charles Cross", 1_700_000, OFFENSIVE_LINE),
            new Player("Phil Haynes", 1_300_000, OFFENSIVE_LINE),
            new Player("Evan Brown", 1_300_000, OFFENSIVE_LINE),
            new Player("Abraham Lucas", 31000_000, OFFENSIVE_LINE),
            new Player("Dre'Mont Jones", 3_000_000, DEFENSIVE_LINE),
            new Player("Bryan Mone", 2_3000_000, DEFENSIVE_LINE),
            new Player("Jarran Reed", 1_200_000, DEFENSIVE_LINE),
            new Player("Uchenna Nwosu", 7_500_000, LINEBACKER),
            new Player("Jordyn Brooks", 2_300_000, LINEBACKER),
            new Player("Devin Bush", 1_800_000, LINEBACKER),
            new Player("Boye Mafe", 1_100_000, LINEBACKER),
            new Player("Quandre Diggs", 13_500_000, DEFENSIVE_BACK),
            new Player("Jamal Adams", 11_000_000, DEFENSIVE_BACK),
            new Player("Julian Love", 6_000_000, DEFENSIVE_BACK),
            new Player("Tariq Woolen", 900_000, DEFENSIVE_BACK)
    ));

    static List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));

    @SuppressWarnings({"java:S106", "unused"})
    public static void main(String[] args) {

        /*
            1. Optional<T> reduce(BinaryOperator accumulator)
                - param = BinaryOperator
                - returns Optional describing the reduced value.

            This is how to do this in reduce.
         */
        Optional<Integer> startersSalary = seahawks.stream()
                .map(Player::getSalary)
                .reduce(Integer::sum);

        startersSalary.ifPresent(integer -> System.out.println("Seahawks salary is " + integer));

        /*
            1b. Same solution using an IntStream and sum()

            NOTE: I personally prefer this.
         */
        int totalSalary = seahawks.stream()
                .mapToInt(Player::getSalary)
                .sum();



        /*
            2. T reduce(T identity, BinaryOperator<T> accum)
            - The identity parameter serves 2 purposes
                a. it is the initial value
                b. it is the default value.
         */
        int total = numbers.stream()
                .reduce(5, Integer::sum);
        System.out.println("Total: " + total);


        /*
            3.  <U> U reduce(U identity, BiFunction<U>, accum, BinaryOperator<U> combiner)
            - the most complicated version.
            - if the stream is parallel, then java runtime will split the stream into
                multiple substreams.
            - The combiner is responsible for combining the substreams back into a single stream

         */
        int parallelSum = numbers.parallelStream()
                .reduce(0, Integer::sum, Integer::sum);
        System.out.println("Total: " + parallelSum);

        /*
            max() and min()
               - These take a Comparator as a param (to compare!)
               - returns an Optional.
         */
        Optional<Integer> max = numbers.stream().max(naturalOrder());
        Optional<Integer> min = numbers.stream().min(naturalOrder());
        max.ifPresent(o -> System.out.println("Max: " + o));
        min.ifPresent(o -> System.out.println("Min: " + o));

    }


        /*
        reduction operations
        - reduce a stream into a single value.
        - given a collection of objects, we can do things like
            - summation
            - max
            - average,
            - anything that collapses it down to a single value

        IDENTITY:
            - initial value of the reduction operation
            - also the default result for an empty stream.

        ACCUMULATOR:
            - a function w/ 2 parameters
                - a partial result of the reduction operation
                - the next element of the stream.

        COMBINER:
            - a function used to combine the partial result of the reduction operation when
                - the reduction is parallelized
                - (or) there is a mismatch between the types of the accum. arguments and the
                type of the accum implementation.
     */
}
