package collections.arraylists.comparisons;

import utils.Generated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static collections.arraylists.comparisons.Position.*;

/*
    Limitation of Comparable is that a class can only have
    a single compareTo() method.
    - This becomes its default for sorting or comparisons.
 */
@SuppressWarnings("all")
@Generated
public class ComparableDemo {

    public static void main(String[] args) {

        /*
        Integers
         */
        List<Member> list = new ArrayList<>();
        list.add(new Member("Michael", 22));
        list.add(new Member("Edward", 46));
        list.add(new Member("Vanessa", 41));
        list.add(new Member("Connor", 11));

        System.out.println("Pre-Sort: ");
        list.forEach(member -> System.out.println("Name: " + member.getName() + ", Age: " + member.getAge()));
        Collections.sort(list);
        System.out.println("Sorted: ");
        list.forEach(member -> System.out.println("Name: " + member.getName() + ", Age: " + member.getAge()));


        /*
            Strings / Objects.
         */
        List<Player> celtics = new ArrayList<>();
        celtics.add(new Player("Marcus Smart", POINT_GUARD));
        celtics.add(new Player("Jaylen Brown", SHOOTING_GUARD));
        celtics.add(new Player("Jayson Tatum", SMALL_FORWARD));
        celtics.add(new Player("Al Horford", POWER_FORWARD));
        celtics.add(new Player("Robert Williams", CENTER));

        System.out.println("Pre-Sort: ");
        printCeltics(celtics);
        Collections.sort(celtics);
        System.out.println("Sorted: ");
        printCeltics(celtics);

    }

    public static void printCeltics(List<Player> players) {
        players.forEach(player -> {
            String name = player.getLastName() + ", " + player.getFirstName();
            String position = player.getPosition().getLabel();
            System.out.println("\t" + name + " (" + position + ")");
        });
    }
}
