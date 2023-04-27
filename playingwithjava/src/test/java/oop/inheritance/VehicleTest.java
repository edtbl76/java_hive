package oop.inheritance;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleTest {


    private Vehicle vehicleUnderTest;

    private final PrintStream printStream = System.out;
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(byteArrayOutputStream));
        vehicleUnderTest = new Vehicle("Toyota", "Blue", 2020, "Tundra");
    }

    @AfterEach
    void tearDown() {
        System.setOut(printStream);
    }

    @Test
    void testPrintDetails() {
        vehicleUnderTest.printDetails();

        // Verify the results
        assertEquals("Manufacturer: Toyota\nColor: Blue\nYear: 2020\nModel: Tundra",
                byteArrayOutputStream.toString().trim());
    }

    @Test
    void testSetTopSpeed() {
        vehicleUnderTest.setTopSpeed(100);

        assertEquals(100, vehicleUnderTest.getTopSpeed());
    }
}
