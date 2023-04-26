package BehavioralDesignPatterns.Observer.ObserverDemo_1;

import java.util.ArrayDeque;
import java.util.Deque;

public class MessageStream extends Subject {

    private Deque<String> messageHistory = new ArrayDeque<>();

    @Override
    void setState(String state) {
        messageHistory.add(state);
        // This is for notifying of the change in state
        this.notifyObservers();
    }

    @Override
    String getState() {
        return messageHistory.getLast();
    }
}
