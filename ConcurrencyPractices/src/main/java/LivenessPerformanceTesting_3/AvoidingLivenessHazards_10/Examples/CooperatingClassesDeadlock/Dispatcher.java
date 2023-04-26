package LivenessPerformanceTesting_3.AvoidingLivenessHazards_10.Examples.CooperatingClassesDeadlock;

import java.util.HashSet;
import java.util.Set;

public class Dispatcher {

    private Set<Taxi> taxis;
    private Set<Taxi> availableTaxis;

    public Dispatcher() {
        taxis = new HashSet<>();
        availableTaxis = new HashSet<>();
    }

    public synchronized void notifyAvailable(Taxi taxi) {
        availableTaxis.add(taxi);
    }

    public synchronized Image getImage() {
        Image image = new Image();
        for (Taxi taxi : taxis) {
            image.drawMarker(taxi.getLocation());
        }
        return image;
    }
}
