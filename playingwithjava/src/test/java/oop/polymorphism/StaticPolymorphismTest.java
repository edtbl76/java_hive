package oop.polymorphism;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StaticPolymorphismTest {

    /*
            Based on the way the method is constructed, I only need
            one test to cover all the methods.
         */
    @Test
    void testAdd3() {
        int result = StaticPolymorphism.add(1, 2, 3, 4);
        assertEquals(10, result);
    }
}
