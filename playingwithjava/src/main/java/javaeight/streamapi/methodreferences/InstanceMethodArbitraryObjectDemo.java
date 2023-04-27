package javaeight.streamapi.methodreferences;

import javaeight.streamapi.methodreferences.utils.Player;
import utils.Generated;

import java.util.ArrayList;
import java.util.List;

import static javaeight.streamapi.methodreferences.utils.Position.*;

@Generated
@SuppressWarnings("java:S106")
public class InstanceMethodArbitraryObjectDemo {

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

    public static void main(String[] args) {

        // code w/o method reference
        @SuppressWarnings({"Convert2MethodRef", "java:S1612"})
        int totalSalary = seahawks
                .stream()
                .mapToInt(value -> value.getSalary())
                .sum();

        System.out.println(totalSalary);

        // w/ method reference
        totalSalary = seahawks
                .stream()
                .mapToInt(Player::getSalary)
                .sum();

        System.out.println(totalSalary);

    }
}
