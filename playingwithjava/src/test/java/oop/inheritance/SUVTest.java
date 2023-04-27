package oop.inheritance;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SUVTest {

    private SUV suvUnderTest;

    private final PrintStream printStream = System.out;
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(byteArrayOutputStream));
        suvUnderTest = new SUV("Toyota", "Black", 2022, "Highlander");
    }

    @AfterEach
    void tearDown() {
        System.setOut(printStream);
    }

    @Test
    void testDisplayFuelCapacity() {
        suvUnderTest.displayFuelCapacity();

        String testData = "Fuel Capacity from the Vehicle class: 90\nFuel Capacity from the SUV Class: 50";
        assertEquals(testData, byteArrayOutputStream.toString().trim());
    }

    @Test
    void testSetTopSpeed() {
        suvUnderTest.setTopSpeed(100);
        assertEquals(100, suvUnderTest.getTopSpeed());
    }
}
