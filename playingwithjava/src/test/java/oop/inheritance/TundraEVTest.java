package oop.inheritance;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TundraEVTest {

    private TundraEV tundraEVUnderTest;

    private final PrintStream printStream = System.out;
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(byteArrayOutputStream));
        tundraEVUnderTest = new TundraEV( "Blue", 2023, "Extended Cab");
    }

    @AfterEach
    void tearDown() {
        System.setOut(printStream);
    }

    // Vehicle Tests
    @Test
    void testSetTopSpeed() {
        tundraEVUnderTest.setTopSpeed(50);
        assertEquals(50, tundraEVUnderTest.getTopSpeed());
    }

    // Truck Tests
    @Test
    void testPrintDetails() {
        tundraEVUnderTest.printDetails();

        String testData = "Manufacturer: Toyota\nColor: Blue\nYear: 2023\nModel: Tundra\nBody Style: Extended Cab";
        assertEquals(testData, byteArrayOutputStream.toString().trim());
    }


    @Test
    void testChargeTruck() {
        // Setup
        // Run the test
        tundraEVUnderTest.chargeTruck();

        // Verify the results
        String testData = "Charging!";
        assertEquals(testData, byteArrayOutputStream.toString().trim());
    }
}
