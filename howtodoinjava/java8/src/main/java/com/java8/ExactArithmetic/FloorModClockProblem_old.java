package com.java8.ExactArithmetic;

import java.util.Arrays;
import java.util.List;

public class FloorModClockProblem_old {

    /*
        Suppose we want to get the position of the hour hand of a clock.

            New Position = (Position + adjustment) % 12

        NOTE: this is using the old mod, which for whatever dumb reason can end up in negative numbers.
     */
    public static void main(String[] args) {
        // These expressions represent initial position of the clock + the adjustment
        List<Integer> expressions = Arrays.asList(10 + 3, 5 + 6, 10 - 27);
        for ( Integer newPosition : expressions)
            System.out.println("The new time is " + newPosition % 12 + " o'clock");
    }
}
