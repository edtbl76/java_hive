public class Context {

    private State state;

    public Context() {
        state = null;
    }

    void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
