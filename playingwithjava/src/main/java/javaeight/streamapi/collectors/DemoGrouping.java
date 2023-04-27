package javaeight.streamapi.collectors;

import javaeight.streamapi.utils.Player;
import javaeight.streamapi.utils.Position;
import utils.Generated;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;
import static javaeight.streamapi.utils.Position.*;
import static javaeight.streamapi.utils.Position.DEFENSIVE_BACK;

@Generated
@SuppressWarnings("java:S106")
public class DemoGrouping {

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

    public static void main(String[] args) {

        /*
            1. groupingBy(Function classifier)
            - groups elements by supplied classifer
            - returns results in a Map
            - like a SQL group by clause
         */
        Map<Position, List<Player>> playerMap = seahawks.stream()
                .collect(groupingBy(Player::getPosition));
        System.out.println(playerMap);

        /*
            1b groupingBy(Function classifier, Collector downstream)
            - takes a second param (collector) which is applied to the results of the classifier
            - This allows us to provide a downstream input.
         */
        Map<Position, Set<Player>> playerSet = seahawks.stream()
                .collect(groupingBy(Player::getPosition, Collectors.toSet()));
        System.out.println(playerSet);

        // chaining Grouping by
        Map<Position, Map<Integer, List<Player>>> chainedGroup = seahawks.stream()
                .collect(groupingBy(Player::getPosition,
                        groupingBy(Player::getSalary)));
        System.out.println(chainedGroup);

        // average salary per position
        Map<Position, Double> averageSalaryPerPosition = seahawks.stream()
                .collect(groupingBy(Player::getPosition,
                        averagingDouble(Player::getSalary)));
        System.out.println(averageSalaryPerPosition);

        // max salary per position
        Map<Position, Optional<Player>> maxSalaryPerPosition = seahawks.stream()
                .collect(groupingBy(Player::getPosition,
                        maxBy(comparing(Player::getSalary))));
        System.out.println(maxSalaryPerPosition);

        /*
            1c groupingBy(classifier, downstream collector, supplier)
            - this version adds a supplier to specifiy the impl type of the Map we want to use
         */
        playerSet = seahawks.stream()
                .collect(groupingBy(Player::getPosition, HashMap::new, toSet()));
        System.out.println(playerSet);

        /*
            groupingByConcurrent()
            - this has the same overloads, but it returns a ConcurrentMap.
         */
        ConcurrentMap<Position, List<Player>> concurrentMap = seahawks.stream()
                .collect(groupingByConcurrent(Player::getPosition));
        System.out.println(concurrentMap);

        /*
            2. partitioningBy(predicate)
            - partitions elements based on supplied predicate.
            - returns a Map of <Boolean, List<T>>
         */
        Predicate<Player> predicate = player -> player.getSalary() > 5_000_000;
        Map<Boolean, List<Player>> salaryPartition = seahawks.stream()
                .collect(partitioningBy(predicate));
        System.out.println(salaryPartition);
    }

}
