package BehavioralDesignPatterns.State.StateDemo_1;

public class FanOffState extends State {

    private Fan fan;

    public FanOffState(Fan fan) {
        this.fan = fan;
    }

    /*
        Individual fanStates only know about the states that they transition to.
     */
    @Override
    public void handleRequest() {
        System.out.println("Turning fan to low");
        fan.setState(fan.getFanLowState());
    }

    @Override
    public String toString() {
        return "Fan is off";
    }
}
