package oop.polymorphism;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RectangleTest {
    Rectangle rectangle = new Rectangle(0d, 0d);

    @AfterEach
    void tearDown() {
        rectangle = null;
    }

    @Test
    void testGetArea() {
        rectangle = new Rectangle(10, 5);
        double result = rectangle.getArea();
        assertEquals(50, result);
    }

    @Test
    void testSetWidth() {
        rectangle.setWidth(10);
        assertEquals(10, rectangle.getWidth());
    }

    @Test
    void testSetHeight() {
        rectangle.setHeight(10);
        assertEquals(10, rectangle.getHeight());
    }

    @Test
    void testToString() {
        String result = rectangle.toString();
        assertEquals("Rectangle(width=0.0, height=0.0)", result);
    }

    @Test
    void testEquals() {
        boolean result = rectangle.equals(new Rectangle(0d, 0d));
        assertTrue(result);
    }

    @Test
    void testCanEqual() {
        Rectangle newRectangle = new Rectangle(0d, 0d);
        boolean result = rectangle.canEqual(newRectangle);
        assertTrue(result);
    }

    @Test
    void testHashCode() {
        int result = rectangle.hashCode();
        assertEquals(new Rectangle(0d,0d).hashCode(), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme