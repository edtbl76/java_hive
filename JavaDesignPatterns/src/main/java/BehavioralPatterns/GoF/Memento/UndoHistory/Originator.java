package BehavioralPatterns.GoF.Memento.UndoHistory;

public class Originator {

    /*
        - Saves its internal state to a memento object
        - restores to a previous state from a memento object
        - only the originator that created a memento should be allowed access to it.
     */

    private int stateId;

    public Originator() {
        this.stateId = 0;
        System.out.println("Originator created [" + stateId + "]");
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        System.out.println("Setting State Id -> " + stateId);
        this.stateId = stateId;
    }

    public Memento save(int stateId) {
        System.out.println("Saving state");
        return new Memento(stateId);
    }

    public void restore(Memento memento) {
        System.out.println("Restoring state to -> " + memento.getState());
        this.stateId = memento.getState();
        System.out.println("State Restored Successfully to -> " + stateId);
    }
}
