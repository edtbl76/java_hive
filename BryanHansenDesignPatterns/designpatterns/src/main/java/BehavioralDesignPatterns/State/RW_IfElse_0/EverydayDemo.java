package BehavioralDesignPatterns.State.RW_IfElse_0;

public class EverydayDemo {

    public static void main(String[] args) {
        Fan fan = new Fan();

        System.out.println(fan);

        fan.pullChain();
        System.out.println(fan);

        fan.pullChain();
        System.out.println(fan);

    }
}
