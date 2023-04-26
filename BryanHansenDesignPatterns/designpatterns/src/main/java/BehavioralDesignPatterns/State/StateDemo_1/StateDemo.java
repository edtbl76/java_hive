package BehavioralDesignPatterns.State.StateDemo_1;

public class StateDemo {

    public static void main(String[] args) {
        Fan fan = new Fan();

        System.out.println(fan);

        fan.pullChain();
        System.out.println(fan);

        fan.pullChain();
        System.out.println(fan);

        fan.pullChain();
        System.out.println(fan);

        fan.pullChain();
        System.out.println(fan);
    }
}
