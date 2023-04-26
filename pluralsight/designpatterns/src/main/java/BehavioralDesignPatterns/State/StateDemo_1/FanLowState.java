package BehavioralDesignPatterns.State.StateDemo_1;

public class FanLowState extends State {

    private Fan fan;

    public FanLowState(Fan fan) {
        this.fan = fan;
    }

    /*
        Individual fanStates only know about the states that they transition to.
     */
    @Override
    public void handleRequest() {
        System.out.println("Turning fan to medium");
        fan.setState(fan.getFanMediumState());
    }

    @Override
    public String toString() {
        return "Fan is on low";
    }
}
