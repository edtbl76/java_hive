package collections.arraylists.comparisons;

import java.util.Comparator;

public class JerseyComparator implements Comparator<Player> {
    @Override
    public int compare(Player player1, Player player2) {
        return player1.getNumber().compareTo(player2.getNumber());
    }
}
