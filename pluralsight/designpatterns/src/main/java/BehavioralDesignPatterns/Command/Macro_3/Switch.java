package BehavioralDesignPatterns.Command.Macro_3;

// Invoker
public class Switch {

    public void storeAndExecute(Command command) {
        command.execute();
    }
}
