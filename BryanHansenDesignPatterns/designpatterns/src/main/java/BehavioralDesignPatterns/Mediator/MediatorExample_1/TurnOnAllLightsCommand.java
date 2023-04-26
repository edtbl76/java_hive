package BehavioralDesignPatterns.Mediator.MediatorExample_1;

public class TurnOnAllLightsCommand implements Command {

    /*
        If you compare this to the Command pattern, the Command pattern started to get smarter and smarter, which
        made it harder to vary the Command and the Object it was trying to work with.

        The mediator separates them. Here the Command is ONLY working w/ the Mediator, allowing the command to
        have simpler code, and the "business logic" to remain in the Mediator
        (Again, a real world example would probably have a contract stated in an abstract top layer,
        extending/implementing to concrete classes to work w/ given commands etc.)
     */
    private Mediator mediator;

    public TurnOnAllLightsCommand(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void execute() {
        mediator.turnOnAllLights();
    }
}
