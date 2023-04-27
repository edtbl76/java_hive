package javaeight.streamapi.collectors;

import javaeight.streamapi.utils.Player;
import utils.Generated;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.counting;
import static javaeight.streamapi.utils.Position.*;

@Generated
@SuppressWarnings("java:S106")
public class DemoAggregation {

    /*
        Collectors that aggregate data in streams
     */

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
            new Player("Abraham Lucas", 1_000_000, OFFENSIVE_LINE),
            new Player("Dre'Mont Jones", 3_000_000, DEFENSIVE_LINE),
            new Player("Bryan Mone", 2_300_000, DEFENSIVE_LINE),
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

    @SuppressWarnings({"StreamToLoop", "SimplifyStreamApiCallChains"})
    public static void main(String[] args) {

        /*
            1. counting()
            - counts the number of input elements

                Collectors.counting() != Stream.count()

                - Collectors can be used as downstream collector in groupingBy(), it is always going to read
                from its direct input rather than stream source.

                - Stream.count() is capable of computing count DIRECTLY from stream source, so it can skip
                source element traversal and it can SKIP the evaluation of intermediate operations.

         */
        long starters = seahawks.stream()
                        .filter(player -> player.getSalary() > 1_000_000)
                        .collect(counting());
        System.out.println("No. of starters w/ over 1M salary " + starters);

        /*
            2. summingInt(ToIntFunction mapper)   (or summingDouble, summinglong)
            - returns a collector that produces the sum of an integer-valued function applied to
            the inputs

                mapToInt().sum() <-- same as Stream.sum(): see above.
         */
        long salaryCount = seahawks.stream()
                .collect(summingInt(Player::getSalary));
        System.out.println("Total Salary: " + salaryCount);


        /*
            3. averagingInt(ToIntFunction mapper)  (averagingDouble, averagingLong)
         */
        double salaryAverage = seahawks.stream()
                .collect(averagingDouble(Player::getSalary));
        System.out.println("Average salary: " + salaryAverage);


        /*
            4.
            minBy(Comparator)
            maxby(Comparator)

            - gives the minimum/maximum element based on the provided comparator
            - (return is wrapped in an Optional)
         */
        Optional<Player> lowestPaid = seahawks.stream()
                .collect(minBy(comparing(Player::getSalary)));
        Optional<Player> highestPaid = seahawks.stream()
                        .collect(maxBy(comparing(Player::getSalary)));

        lowestPaid.ifPresent(player -> System.out.println("Lowest Paid: " +  player.getName()));
        highestPaid.ifPresent(player -> System.out.println("Highest Paid: " + player.getName()));

        /*
            5. summarizingInt(ToIntFunction mapper)     (summarizingDouble, summarizingLong)
            - returns a Collector that applies an <int> mapping function to each element
            - returns summary statistics for resulting values.
                - count, sum, min, max and average
         */
        IntSummaryStatistics summaryStatistics = seahawks.stream()
                .map(Player::getSalary)
                .collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println(summaryStatistics);

        /*
            6. joining()
                - returns a collector that concatenates input elements into a string in the order in
                which the elements are encountered.
                - (includes overloaded versions w/ delimiters, prefix/suffix)
         */
        String useless = seahawks.stream()
                .map(player -> player.getName().split(" ")[1])
                .collect(joining());
        System.out.println(useless);

        String spaced = seahawks.stream()
                .map(player -> player.getName().split(" ")[1])
                .collect(joining(" "));
        System.out.println(spaced);

        String prefixAndSuffix = seahawks.stream()
                .map(Player::getName)
                .collect(joining(", ", "The Seahawks: ", ", Go 'hawks!"));
        System.out.println(prefixAndSuffix);


    }




}
