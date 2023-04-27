package oop.polymorphism;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DynamicPolymorphismTest {

    final Shape[] shapes = new Shape[]{
            new Circle(3),
            new Rectangle(2, 3)
    };
    // Test Methods
    @Test
    void testCircleGetArea() {
        assertEquals(Math.PI * 9, shapes[0].getArea());
    }

    @Test
    void testRectangleGetArea() {
        assertEquals(6, shapes[1].getArea());
    }


    // Test Class Equality (Dynamic Polymorphism)
    @Test
    void testCircleClassType() {
        boolean result = shapes[0].getClass().equals(Circle.class);
        assertTrue(result);
    }

    @Test
    void testRectangleClassType() {
        boolean result = shapes[1].getClass().equals(Rectangle.class);
        assertTrue(result);
    }

    // Test Class Equality - Directly
    @Test
    void testDirectCircle() {
        Shape shape = new Circle(1);
        assertEquals(shape.getClass(), Circle.class);
    }

    @Test
    void testDirectRectangle() {
        Shape shape = new Rectangle(1, 1);
        assertEquals(shape.getClass(), Rectangle.class);
    }






}
