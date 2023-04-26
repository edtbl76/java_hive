package com.java7.changes;

public class MultiCatchExceptions {

    public static void main(String[] args) {

        try {
            throw new NullPointerException();
        } catch ( NullPointerException | IndexOutOfBoundsException ex) {
            throw ex;
        }
    }
}
