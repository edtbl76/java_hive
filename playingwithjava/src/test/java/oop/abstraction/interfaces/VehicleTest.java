package oop.abstraction.interfaces;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;

class VehicleTest {

    final Vehicle vehicle = spy(Vehicle.class);
    private final PrintStream printStream = System.out;
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(byteArrayOutputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(printStream);
    }

    @Test
    void testStartVehicle() {
        vehicle.startVehicle();
        assertEquals("starting", byteArrayOutputStream.toString().trim());
    }

    @Test
    void testHonkHorn() {
        // Static, so we have to define it from the interface reference
        Vehicle.honkHorn();
        assertEquals("beep beep", byteArrayOutputStream.toString().trim());
    }
}
