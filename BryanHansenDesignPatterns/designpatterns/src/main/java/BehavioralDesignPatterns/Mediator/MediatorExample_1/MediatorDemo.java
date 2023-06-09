package BehavioralDesignPatterns.Mediator.MediatorExample_1;

public class MediatorDemo {

    public static void main(String[] args) {
        Mediator mediator = new Mediator();

        Light bedroomLight = new Light("Bedroom");
        Light kitchenLight = new Light("Kitchen");

        mediator.registerLight(bedroomLight);
        mediator.registerLight(kitchenLight);

        Command turnOnAllLightsCommand = new TurnOnAllLightsCommand(mediator);
        Command turnOffAllLightsCommand = new TurnOffAllLightsCommand(mediator);

        turnOnAllLightsCommand.execute();
        turnOffAllLightsCommand.execute();

    }
}
