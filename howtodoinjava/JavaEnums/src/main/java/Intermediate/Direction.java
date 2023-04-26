package Intermediate;

public enum Direction {

    // fields
    EAST(0), WEST(180), NORTH(90), SOUTH(270);

    // constructor
    private Direction(final int angle) {
        this.angle = angle;
    }

    // state
    private int angle;

    public int getAngle() {
        return angle;
    }
}
