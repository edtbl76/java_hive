package collections.arraylists.comparisons;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import utils.Generated;

@Getter @Setter
@EqualsAndHashCode
@ToString
@Generated
public class Player implements Comparable<Player> {

    private String firstName;
    private String lastName;
    private Position position;
    private Integer number;

    public Player(String name, Position position) {
        super();
        String[] fullName = name.split(" ");
        this.firstName = fullName[0];
        this.lastName = fullName[1];
        this.position = position;
    }

    public Player(String name, Position position, int number) {
        super();
        String[] fullName = name.split(" ");
        this.firstName = fullName[0];
        this.lastName = fullName[1];
        this.position = position;
        this.number = number;
    }

    /*
        String compareTo() comparison.
        (We're basically calling String.commpareTo)
     */
    @Override
    public int compareTo(Player player) {
        return this.lastName.compareTo(player.getLastName());
    }
}
