package collections.arraylists.comparisons;

import utils.Generated;

import java.util.Comparator;

@Generated
class FirstNameComparator implements Comparator<Player> {

    @Override
    public int compare(Player player1, Player player2) {
        return player1.getFirstName().compareTo(player2.getFirstName());
    }
}
