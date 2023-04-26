package Fundamentals_1.ComposingObjects_4.Classes;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

//@ThreadSafe
public class DelegatingVehicleTracker {
    private final ConcurrentMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        locations = new ConcurrentHashMap<>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
        return unmodifiableMap;
    }

    public Point getLocation(String locationId) {
        return locations.get(locationId);
    }

    public void setLocation(String locationId, int x, int y) {
        if (locations.replace(locationId, new Point(x, y)) == null) {
            throw new IllegalArgumentException("invalid vehicle name: " + locationId);
        }
    }


}
