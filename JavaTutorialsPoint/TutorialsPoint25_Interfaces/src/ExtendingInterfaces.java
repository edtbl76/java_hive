@SuppressWarnings("unused")
interface TeamSport {
    void setHome(String name);
    void setVisitor(String name);
}

@SuppressWarnings("unused")
interface Football extends TeamSport {
    void scoreTouchdown(int sixPoints);
    void endOfQuarter(int quarter);
}

@SuppressWarnings("unused")
interface Hockey extends TeamSport {
    void scoreGoal(int onePoint);
    void endOfPeriod(int period);
}

@SuppressWarnings("unused")
public class ExtendingInterfaces {

    // Doesn't Do Anything. I'm just an example
}
