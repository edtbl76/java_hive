package oop.encapsulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleTest {

    private Rectangle rectangleUnderTest;

    @BeforeEach
    void setUp() {
        rectangleUnderTest = new Rectangle(5, 10);
    }

    @Test
    void testGetArea() {
        assertEquals(50, rectangleUnderTest.getArea());
    }
}
