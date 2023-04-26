package com.emangini;

public class DogException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public DogException(String message) {
        super(message);
    }
}
