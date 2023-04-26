package BehavioralDesignPatterns.Command.Switch_1;

// Concrete Commands do the actual work.
public class OnCommand implements Command {

    private Light light;

    public OnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}
