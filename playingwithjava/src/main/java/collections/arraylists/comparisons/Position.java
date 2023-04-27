package collections.arraylists.comparisons;

import lombok.Getter;
import utils.Generated;

@Getter
@Generated
public enum Position {
    POINT_GUARD("PG"),
    SHOOTING_GUARD("SG"),
    SMALL_FORWARD("SF"),
    POWER_FORWARD("PF"),
    CENTER("C");

    private final String label;

    Position(String label) {
        this.label = label;
    }
}
