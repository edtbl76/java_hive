package BehavioralDesignPatterns.Command.Macro_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandDemo {

    public static void main(String[] args) {

        Light bedroomLight = new Light();
        Light kitchenLight = new Light();

        Switch lightSwitch = new Switch();

        /*
            The client is issuing a command TO BE EXECUTED. It isn't
            executing it directly. This is important.

            By deferring the command to be executed it opens up other options
            for managing state and other goodies.
        */
        Command toggleCommand = new ToggleCommand(bedroomLight);
        lightSwitch.storeAndExecute(toggleCommand);
        lightSwitch.storeAndExecute(toggleCommand);
        lightSwitch.storeAndExecute(toggleCommand);

        /*
            Macros allow us to operate on MULTIPLE instances of
            the same object.
         */
        List<Light> lights = new ArrayList<>();
        lights.addAll(Arrays.asList(bedroomLight, kitchenLight));
        Command allLightsCommand = new AllLightsCommand(lights);

        lightSwitch.storeAndExecute(allLightsCommand);
    }
}
