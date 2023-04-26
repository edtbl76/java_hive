package BehavioralDesignPatterns.Command.Macro_3;

// Concrete Commands do the actual work.
public class ToggleCommand implements Command {

    private Light light;

    public ToggleCommand(Light light) {
        this.light = light;
    }

    /*
        This is the only thing that really changes..
        i.e. the command we are executing

     */
    @Override
    public void execute() {
        light.toggle();
    }
}
