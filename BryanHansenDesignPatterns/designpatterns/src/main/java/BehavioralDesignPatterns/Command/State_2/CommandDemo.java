package BehavioralDesignPatterns.Command.State_2;

public class CommandDemo {

    public static void main(String[] args) {

        Light light = new Light();
        Switch lightSwitch = new Switch();

        /*
            The client is issuing a command TO BE EXECUTED. It isn't
            executing it directly. This is important.

            By deferring the command to be executed it opens up other options
            for managing state and other goodies.
        */
        Command  toggleCommand = new ToggleCommand(light);
        lightSwitch.storeAndExecute(toggleCommand);
        lightSwitch.storeAndExecute(toggleCommand);
        lightSwitch.storeAndExecute(toggleCommand);
        lightSwitch.storeAndExecute(toggleCommand);
    }
}
