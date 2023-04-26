package com.basics.ControlStatements.While;

public class CountingToN {
    public static void main(String[] args) {

        /*
        This is a common use case for a while loop.
        Typically the condition for the while loop will be a user/config driven limit, and the while loop will
        increment forward until it reaches that high watermark.

        This could be some sort of counter, or it could transform other data in response to the iteration.
            EX: Some compression algorithms, smoothing filters/averaging altorithms, or mathematical functions
            can be configured to run through multiple passes for better granularity, statistical relevence, or even
            confidence in heuristic/probablistic analysis.
         */
        int i = 1;
        while (i <= 5) {
            System.out.println(i);
            i++;
        }
    }
}
