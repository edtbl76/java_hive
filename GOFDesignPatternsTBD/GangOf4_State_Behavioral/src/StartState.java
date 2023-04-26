public class StartState implements State {

    public void doAction(Context context) {
        System.out.println("Player is in starting state.");
        context.setState(this);
    }

    public String toString() {
        return "Start State";
    }
}
