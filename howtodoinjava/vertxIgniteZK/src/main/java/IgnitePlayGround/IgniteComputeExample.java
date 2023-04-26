package IgnitePlayGround;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.lang.IgniteCallable;

import java.util.ArrayList;
import java.util.Collection;

public class IgniteComputeExample {

    public static void main(String[] args) {

        try (Ignite ignite = Ignition.start()) {
            Collection<IgniteCallable<Integer>> calls = new ArrayList<>();

            for (final String word: "Count characters using callable".split(" "))
                calls.add(word::length);

            Collection<Integer> result = ignite.compute().call(calls);

            int sum = result
                    .stream()
                    .mapToInt(Integer::intValue)
                    .sum();

            System.out.println("Total number of characters is: " + sum);

        }
    }
}
