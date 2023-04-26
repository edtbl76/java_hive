package Fundamentals_1.ComposingObjects_4.Classes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//@ThreadSafe
public class MonitorVehicleTracker {
    //@GuardedBy("this")
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String locationId) {
        MutablePoint location = locations.get(locationId);
        return location == null ? null : new MutablePoint(location);
    }

    public synchronized void setLocation(String locationId, int x, int y) {
        MutablePoint location = locations.get(locationId);
        if (location == null) {
            throw new IllegalArgumentException("No such ID: " + locationId);
        }
        location.setX(x);
        location.setY(y);
    }

    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> map) {
        Map<String, MutablePoint> result = new HashMap<>();
        for (String locationId : map.keySet()) {
            result.put(locationId, new MutablePoint(map.get(locationId)));
        }
        return Collections.unmodifiableMap(result);
    }
}
