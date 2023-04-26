package BehavioralDesignPatterns.Mediator.MediatorExample_1;

import java.util.ArrayList;
import java.util.List;

public class Mediator {

    /*
        Only one Mediator in this example, so we'll just use a regular clas.
        If there were multiple Mediators, we'd create an abstract-concrete hierarchy
     */

    private List<Light> lights = new ArrayList<>();

    public void registerLight(Light light) {
        lights.add(light);
    }

    public void turnOnAllLights() {
        lights.forEach(light -> {
            if (!light.isOn())
                light.toggle();
        });
    }

    public void turnOffAllLights() {
        lights.forEach(light -> {
            if(light.isOn())
                light.toggle();
        });

    }
}
