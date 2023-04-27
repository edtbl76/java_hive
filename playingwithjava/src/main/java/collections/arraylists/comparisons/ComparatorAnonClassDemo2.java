package collections.arraylists.comparisons;

import utils.Generated;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static collections.arraylists.comparisons.Position.*;

/*
    Enhancements over Comparator Demo.
    1.) Collections.sort() replaced w/ List.sort()
    2.) External comparators are replaced w/ anonymous classes.... one ugly change!
 */
@Generated
@SuppressWarnings("all")
public class ComparatorAnonClassDemo2 {

    public static void main(String[] args) {
        List<Player> celtics = new ArrayList<>();
        celtics.add(new Player("Marcus Smart", POINT_GUARD, 36));
        celtics.add(new Player("Jaylen Brown", SHOOTING_GUARD, 7));
        celtics.add(new Player("Jayson Tatum", SMALL_FORWARD, 0));
        celtics.add(new Player("Al Horford", POWER_FORWARD, 42));
        celtics.add(new Player("Robert Williams", CENTER, 44));

        System.out.println("Pre-Sort: ");
        printCeltics(celtics);

        // Sort by last name
        celtics.sort(new Comparator<>() {
            @Override
            public int compare(Player player1, Player player2) {
                return player1.getLastName().compareTo(player2.getLastName());
            }
        });
        System.out.println("Sorted by last name: ");
        printCeltics(celtics);

        // Sort by first name
        celtics.sort(new Comparator<Player>() {
            @Override
            public int compare(Player player1, Player player2) {
                return player1.getFirstName().compareTo(player2.getFirstName());
            }
        });
        System.out.println("Sorted by first name: ");
        printCeltics(celtics);

        // Sort by jersey number
        celtics.sort(new Comparator<Player>() {
            @Override
            public int compare(Player player1, Player player2) {
                return player1.getNumber().compareTo(player2.getNumber());
            }
        });
        System.out.println("Sorted by jersey: ");
        printCeltics(celtics);
    }

    public static void printCeltics(List<Player> players) {
        players.forEach(player -> {
            String name = player.getLastName() + ", " + player.getFirstName();
            String position = player.getPosition().getLabel();
            System.out.println("\t" + name + " (" + position + "), #" + player.getNumber());
        });
    }
}
