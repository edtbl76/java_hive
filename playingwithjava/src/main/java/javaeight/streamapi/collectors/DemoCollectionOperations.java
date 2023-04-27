package javaeight.streamapi.collectors;

import javaeight.streamapi.utils.Player;
import javaeight.streamapi.utils.Position;
import utils.Generated;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static javaeight.streamapi.utils.Position.*;

@Generated
@SuppressWarnings({"java:S106", "java:S6204"})
public class DemoCollectionOperations {

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

    static List<String> list = new ArrayList<>(List.of("done", "far", "away", "done"));

    public static void main(String[] args) {

        // 1. Collectors.toList() [NOTE: Stream.toList() is immutable, Collectors.toList() is not]
        List<String> names = seahawks.stream()
                .map(Player::getName)
                .collect(toList());
        System.out.println(names);

        // 2. Collectors.toSet()
        Set<Position> distinctPositions = seahawks.stream()
                .map(Player::getPosition)
                .collect(toSet());
        System.out.println(distinctPositions);

        /*
            3. Collectors.toCollection(Supplier)
            - Collects input elements into a new collection
            - Supplier supplies the collection we want

            // changing this from ArrayList to LinkedList
         */
        List<String> linkedNames = seahawks.stream()
                .map(Player::getName)
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(linkedNames);

        // 4. toMap(keyMapper, valueMapper) parameters map information
        // NOTE: If the list has duplicate elements it will throw an exception because map keys must be unique.
        Map<String, Integer> playerSalaryMap = seahawks.stream()
                .collect(Collectors.toMap(Player::getName, Player::getSalary));
        System.out.println(playerSalaryMap);

        // 4b toMap() w/ BinaryOperator ot handle the case of duplicates.
        // NOTE: In this example, we're just picking the first one.

        Map<String, Integer> wordLengthMap = list.stream()
                .collect(Collectors.toMap(s -> s, String::length, (s1, s2) -> s1));
        System.out.println(wordLengthMap);

        // 4c toMap() w/ a Supplier to choose the map implementation
        wordLengthMap = list.stream()
                .collect(Collectors.toMap(s -> s, String::length, (s1, s2) -> s1, HashMap::new));
        System.out.println(wordLengthMap);


        /*
            "Mortal Kombat"
            5. collectingAndThen(Collector downstream, Function finisher)
            - returns a collector that accumulates the input elements and then performs
            the finishing function.


         */
        // EX: Collect elements into a LIST and then convert it into an unmodifiable/immutable list
        List<String> immutableList = list.stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
        System.out.println(immutableList);


    }



}
