package collections.arraylists.comparisons;

import utils.Generated;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static collections.arraylists.comparisons.Position.*;

/*
    - replaces lambdas w/ Comparator.comparing()
 */
@SuppressWarnings("all")
@Generated
public class ComparatorComparingDemo4 {

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
        celtics.sort(Comparator.comparing(Player::getLastName));
        System.out.println("Sorted by last name: ");
        printCeltics(celtics);

        // Sort by first name
        celtics.sort(Comparator.comparing(Player::getFirstName));
        System.out.println("Sorted by first name: ");
        printCeltics(celtics);

        // Sort by jersey number
        celtics.sort(Comparator.comparing(Player::getNumber));
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
