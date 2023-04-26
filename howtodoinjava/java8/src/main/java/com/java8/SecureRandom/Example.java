package com.java8.SecureRandom;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Arrays;

public class Example {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
        SecureRandom secureRandomGenerator = SecureRandom.getInstance("SHA1PRNG", "SUN");

        // get 128 random bytes
        byte[] randomBytes = new byte[128];
        secureRandomGenerator.nextBytes(randomBytes);
        System.out.println(Arrays.toString(randomBytes));

        // get random int
        int r = secureRandomGenerator.nextInt();
        System.out.println(r);

        // get random int in a range of numbers
        int randInRange = secureRandomGenerator.nextInt(99999);
        System.out.println(randInRange);

    }
}
