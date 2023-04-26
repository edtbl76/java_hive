package BehavioralDesignPatterns.Command.State_2;

// Invoker
public class Switch {

    public void storeAndExecute(Command command) {
        command.execute();
    }
}
