package oop.inheritance;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TruckTest {

    private Truck truckUnderTest;

    private final PrintStream printStream = System.out;
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(byteArrayOutputStream));
        truckUnderTest = new Truck("Toyota", "Blue", 2020, "Tundra", "Extended Cab");
    }

    @AfterEach
    void tearDown() {
        System.setOut(printStream);
    }

    @Test
    void testPrintDetails() {
        // Setup
        // Run the test
        truckUnderTest.printDetails();

        // Verify the results
        String testData = "Manufacturer: Toyota\nColor: Blue\nYear: 2020\nModel: Tundra\nBody Style: Extended Cab";
        assertEquals(testData, byteArrayOutputStream.toString().trim());
    }

    @Test
    void testOpenTailgate() {
        truckUnderTest.openTailgate();
        String testData = "Tailgate is open";
        assertEquals(testData, byteArrayOutputStream.toString().trim());
    }

    @Test
    void testDisplayFuelCapacity() {
        truckUnderTest.displayFuelCapacity();

        String testData = "Fuel Capacity from the Vehicle class: 90\nFuel Capacity from the Truck Class: 200";
        assertEquals(testData, byteArrayOutputStream.toString().trim());
    }

    @Test
    void testSetTopSpeed() {
        truckUnderTest.setTopSpeed(100);
        assertEquals(100, truckUnderTest.getTopSpeed());
    }


}
