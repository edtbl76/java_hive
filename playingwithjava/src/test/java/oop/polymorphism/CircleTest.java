package oop.polymorphism;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CircleTest {
    Circle circle = new Circle(0d);

    @Test
    void testGetArea() {
        circle = new Circle(5);
        double result = circle.getArea();
        assertEquals(Math.PI * 25, result);
    }

    @Test
    void testSetRadius() {
        circle.setRadius(10);
        assertEquals(10, circle.getRadius());

    }

    @Test
    void testToString() {
        String result = circle.toString();
        assertEquals("Circle(radius=0.0)", result);
    }

    @Test
    void testEquals() {
        boolean result = circle.equals(new Circle(0d));
        assertTrue(result);
    }

    @Test
    void testCanEqual() {
        boolean result = circle.canEqual(new Circle(0d));
        assertTrue(result);
    }

    @Test
    void testHashCode() {
        int result = circle.hashCode();
        assertEquals(new Circle(0d).hashCode(), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme