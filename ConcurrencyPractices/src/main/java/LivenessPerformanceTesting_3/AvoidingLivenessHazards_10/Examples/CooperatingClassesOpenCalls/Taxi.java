package LivenessPerformanceTesting_3.AvoidingLivenessHazards_10.Examples.CooperatingClassesOpenCalls;

/*
    Thread Safe version of the shitty class we wrote in CooperatingClassesDeadlock
 */
public class Taxi {
    private Point location;
    private Point destination;

    private final Dispatcher dispatcher;

    public Taxi(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public synchronized Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        boolean reachedDestination;

        synchronized (this) {
            this.location = location;
            reachedDestination = location.equals(destination);
        }
        if (reachedDestination) {
            dispatcher.notifyAvailable(this);
        }
    }
}
