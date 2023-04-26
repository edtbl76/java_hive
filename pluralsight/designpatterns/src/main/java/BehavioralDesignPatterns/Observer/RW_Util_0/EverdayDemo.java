package BehavioralDesignPatterns.Observer.RW_Util_0;


import java.util.Observable;
import java.util.Observer;

public class EverdayDemo {

    public static void main(String[] args) {

        TwitterStream messageStream = new TwitterStream();

        Client client1 = new Client("George");
        Client client2 = new Client("Gracie");

        messageStream.addObserver(client1);
        messageStream.addObserver(client2);

        messageStream.someoneTweeted();
    }
}


class TwitterStream extends Observable {

    public void someoneTweeted() {
        setChanged();
        notifyObservers();
    }
}

class Client implements Observer {
    private String name;

    public Client(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
       System.out.println("Updated " + name + "s stream, someone tweeted something");
    }
}
