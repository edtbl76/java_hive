package com.java8.DateTimeUpdates;

import java.time.Duration;
import java.time.Instant;

public class DurationExample {

    /*
        Duration deals w/ small units of time (from Milliseconds -> hours)
        - most suitable for working w/ application code.
     */
    public static void main(String[] args) {
        Duration duration = Duration.ofMillis(5000);
        System.out.println(duration.toString());

        duration = Duration.ofSeconds(60);
        System.out.println(duration.toString());

        duration = Duration.ofMinutes(10);
        System.out.println(duration.toString());

        duration = Duration.ofHours(2);
        System.out.println(duration.toString());

        duration = Duration.between(Instant.now(), Instant.now().plus(Duration.ofMinutes(10)));
        System.out.println(duration.toString());

    }
}
