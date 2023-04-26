package LivenessPerformanceTesting_3.AvoidingLivenessHazards_10.Examples.CooperatingClassesOpenCalls;

import java.util.HashSet;
import java.util.Set;

public class Dispatcher {

    private final Set<Taxi> taxis;
    private final Set<Taxi> availableTaxis;

    public Dispatcher() {
        taxis = new HashSet<>();
        availableTaxis = new HashSet<>();
    }

    public synchronized void notifyAvailable(Taxi taxi) {
        availableTaxis.add(taxi);
    }

    public Image getImage() {
        Set<Taxi> defensiveCopy;

        synchronized (this) {
            defensiveCopy = new HashSet<>(taxis);
        }
        Image image = new Image();
        for (Taxi taxi : defensiveCopy) {
            image.drawMarker(taxi.getLocation());
        }
        return image;
    }
}
