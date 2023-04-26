package LivenessPerformanceTesting_3.AvoidingLivenessHazards_10.Examples.CooperatingClassesDeadlock;

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

    public synchronized void setLocation(Point location) {
        this.location = location;
        if (location.equals(destination)) {
            dispatcher.notifyAvailable(this);
        }
    }
}
