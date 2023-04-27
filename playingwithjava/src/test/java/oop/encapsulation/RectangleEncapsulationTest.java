package oop.encapsulation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleEncapsulationTest {
    Rectangle rectangle;

    @BeforeEach
    void setUp() {
        rectangle = new Rectangle();
    }

    @AfterEach
    void tearDown() {
        rectangle = null;
    }

    @Test
    void testRectangle() {
       Rectangle rectangle = new Rectangle();
       assertEquals(0, rectangle.getLength());
       assertEquals(0, rectangle.getWidth());
    }

    @Test
    void testGetArea() {
        rectangle = new Rectangle(7, 12);
        int result = rectangle.getArea();
        assertEquals(84, result);
    }

    @Test
    void testSetLength() {
        rectangle.setLength(9);
        assertEquals(9, rectangle.getLength());
    }

    @Test
    void testSetWidth() {
        rectangle.setWidth(5);
        assertEquals(5, rectangle.getWidth());
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme