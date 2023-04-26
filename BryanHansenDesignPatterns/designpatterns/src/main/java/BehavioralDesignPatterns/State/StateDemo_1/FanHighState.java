package BehavioralDesignPatterns.State.StateDemo_1;

public class FanHighState extends State {

    private Fan fan;

    public FanHighState(Fan fan) {
        this.fan = fan;
    }

    /*
        Individual fanStates only know about the states that they transition to.
     */
    @Override
    public void handleRequest() {
        System.out.println("Turning fan off");
        fan.setState(fan.getFanOffState());
    }

    @Override
    public String toString() {
        return "Fan is on high";
    }
}
