package com.java8.ExactArithmetic;

import java.util.Arrays;
import java.util.List;

public class FloorModClockProblem_Java8 {

    // check out the OLD version of this problem first.
    // The last case correctly reflects 7 o'clock, instead of -5 o'clock
    public static void main(String[] args) {

        List<Integer> positions = Arrays.asList(10, 5, 10);
        List<Integer> adjustments = Arrays.asList(3, 6, -27);

        for (int i = 0; i < positions.size(); i++) {
            // We could probably put the entire calculation in the print statement, but this is more readable.
            int currentPosition = positions.get(i) + adjustments.get(i);
            System.out.println("The time is now " + Math.floorMod(currentPosition, 12) + " o'clock");
        }

    }
}
