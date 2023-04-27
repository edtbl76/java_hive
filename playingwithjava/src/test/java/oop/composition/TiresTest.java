package oop.composition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TiresTest {

    private Tires tiresUnderTest;

    @BeforeEach
    void setUp() {
        tiresUnderTest = new Tires();
    }

    @Test
    void testEquals() {
        assertEquals(tiresUnderTest, new Tires());
    }

    @Test
    void testCanEqual() {
        assertTrue(tiresUnderTest.canEqual(new Tires()));
    }

    @Test
    void testHashCode() {
        assertEquals(new Tires().hashCode(), tiresUnderTest.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(new Tires().toString(), tiresUnderTest.toString());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 6})
    void testSetCount(int numberOfTires) {
        tiresUnderTest.setCount(numberOfTires);
        assertEquals(numberOfTires, tiresUnderTest.getCount());
    }
}
