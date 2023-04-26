package BehavioralDesignPatterns.Mediator.MediatorExample_1;

// "other colleage"
public class Light {

    /*
        This introduces state inside the Receiver.
        
     */
    private boolean isOn = false;
    private String location = "";

    public Light() {
    }

    public Light(String location) {
        this.location = location;
    }

    public boolean isOn() {
        return isOn;
    }

    public void toggle() {
        if(isOn) {
            off();
            isOn = false;
        } else {
            on();
            isOn = true;
        }
    }

    public void on() {
        System.out.println("Light switched on");
    }

    public void off() {
        System.out.println("Light switched off");
    }

    public String getLocation() {
        return location;
    }
}
