package BehavioralDesignPatterns.Command.Macro_3;

import java.util.List;

public class AllLightsCommand implements Command {

    private List<Light> lights;

    public AllLightsCommand(List<Light> lights) {
        this.lights = lights;
    }

    /*
        We've modified the execute so that when we turn on/off all lights it
        will only toggle the lights if their states need to change.
        (i.e. lights already on don't need to be switched on.)
     */
    @Override
    public void execute() {
        lights.forEach(light -> {
            if(light.isOn())
                light.toggle();
        });
    }
}
