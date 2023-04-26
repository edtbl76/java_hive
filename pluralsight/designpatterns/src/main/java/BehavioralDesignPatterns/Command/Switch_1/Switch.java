package BehavioralDesignPatterns.Command.Switch_1;

// Invoker
public class Switch {

    public void storeAndExecute(Command command) {
        command.execute();
    }
}
