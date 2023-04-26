package BehavioralDesignPatterns.State.StateDemo_1;

public class FanMediumState extends State {

    private Fan fan;

    public FanMediumState(Fan fan) {
        this.fan = fan;
    }

    /*
        Individual fanStates only know about the states that they transition to.
     */
    @Override
    public void handleRequest() {
        System.out.println("Turning fan to high");
        fan.setState(fan.getFanHighState());
    }

    @Override
    public String toString() {
        return "Fan is on medium";
    }
}
