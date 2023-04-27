package oop.composition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EngineTest {

    private Engine engineUnderTest;

    @BeforeEach
    void setUp() {
        engineUnderTest = new Engine();
    }

    @Test
    void testEquals() {
        assertEquals(engineUnderTest, new Engine());
    }

    @Test
    void testCanEqual() {
        assertTrue(engineUnderTest.canEqual(new Engine()));
    }

    @Test
    void testHashCode() {
        assertEquals(new Engine().hashCode(), engineUnderTest.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(new Engine().toString(), engineUnderTest.toString());
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8})
    void testSetCylinders(int cylinders) {
        engineUnderTest.setCylinders(cylinders);
        assertEquals(cylinders, engineUnderTest.getCylinders());
    }
}
