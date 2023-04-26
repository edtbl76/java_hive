package BehavioralDesignPatterns.Command.Macro_3;

// Receiver
public class Light {

    /*
        This introduces state inside the Receiver.
        
     */
    private boolean isOn = false;

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
}
