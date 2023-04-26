package BehavioralDesignPatterns.Observer.ObserverDemo_1;

public abstract class Observer {
    protected Subject subject;
    abstract void update();
}
