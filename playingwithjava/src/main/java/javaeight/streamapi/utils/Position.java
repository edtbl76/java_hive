package javaeight.streamapi.utils;

public enum Position {
    QUARTERBACK("QB"),
    RUNNING_BACK("RB"),
    WIDE_RECEIVER("WR"),
    TIGHT_END("TE"),
    OFFENSIVE_LINE("OL"),
    DEFENSIVE_LINE("DL"),
    LINEBACKER("LB"),
    DEFENSIVE_BACK("DB");


    @SuppressWarnings({"FieldCanBeLocal", "unused", "java:S1700"})
    private final String position;
    Position(String position) {
        this.position = position;
    }
}
