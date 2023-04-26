package BehavioralDesignPatterns.Observer.ObserverDemo_1;

public class ObserverDemo {

    public static void main(String[] args) {

        Subject subject = new MessageStream();

        // Registering our clients
        PhoneClient phoneClient = new PhoneClient(subject);
        TabletClient tabletClient = new TabletClient(subject);

        // Adding messages (notification is abstracted... it just happens when state is created/changed etc.)
        // This is ~reactive.
        phoneClient.addMessage("Here is a new message!");
        tabletClient.addMessage("Another new message!");

    }
}
