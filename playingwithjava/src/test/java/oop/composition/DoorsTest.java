package oop.composition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DoorsTest {

    private Doors doorsUnderTest;

    @BeforeEach
    void setUp() {
        doorsUnderTest = new Doors();
    }

    @Test
    void testEquals() {
        assertEquals(doorsUnderTest, new Doors());
    }

    @Test
    void testCanEqual() {
        assertTrue(doorsUnderTest.canEqual(new Doors()));
    }

    @Test
    void testHashCode() {
        assertEquals(new Doors().hashCode(), doorsUnderTest.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(new Doors().toString(), doorsUnderTest.toString());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 4, 5})
    void testSetCount(int numberOfDoors) {
        doorsUnderTest.setCount(numberOfDoors);
        assertEquals(numberOfDoors, doorsUnderTest.getCount());
    }
}
