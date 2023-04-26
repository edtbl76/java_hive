package com.emangini.java11.OptionalUpdates;

import org.junit.Assert;

import java.util.Optional;

public class OptionalIsEmptyExample {

    /*
        Optional
            - container object which may/may not contain a null value.
            - if no value is present, the value is considered empty.

        The examples below simply recommend not using negation.
     */
    public static void main(String[] args) {

        String currentTime = null;

        // Negative Condition... don't do this.
        boolean negativeResult = !Optional.ofNullable(currentTime).isPresent();

        // Positive Condition
        boolean positiveResult = Optional.ofNullable(currentTime).isEmpty();


        Assert.assertTrue(negativeResult);
        Assert.assertTrue(positiveResult);

        currentTime = "now";
        negativeResult = !Optional.ofNullable(currentTime).isPresent();
        positiveResult = Optional.ofNullable(currentTime).isEmpty();

        Assert.assertFalse(negativeResult);
        Assert.assertFalse(positiveResult);


    }
}
