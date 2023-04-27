package oop.polymorphism;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShapeTest {
    final Shape shape = new Shape();

    @Test
    void testGetArea() {
        double result = shape.getArea();
        Assertions.assertEquals(0d, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme