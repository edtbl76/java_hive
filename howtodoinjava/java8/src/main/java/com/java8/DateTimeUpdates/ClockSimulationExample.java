package com.java8.DateTimeUpdates;

import java.time.Clock;
import java.time.Duration;
import java.time.ZoneId;

public class ClockSimulationExample {
    public static void main(String[] args) {
        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock);
        System.out.println(clock.instant().toString());
        System.out.println(clock.getZone());

        Clock anotherClock = Clock.system(ZoneId.of("America/Yellowknife"));
        System.out.println(anotherClock);
        System.out.println(anotherClock.instant());
        System.out.println(anotherClock.getZone());

        Clock forwardedClock = Clock.tick(anotherClock, Duration.ofSeconds(600));
        System.out.println(forwardedClock.instant().toString());
    }
}
