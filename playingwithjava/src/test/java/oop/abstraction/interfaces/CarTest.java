package oop.abstraction.interfaces;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarTest {
    final Car car = new Car();
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
    void testCleanVehicle() {
        car.cleanVehicle();
        String testData = "Cleaning my car.";
        String result = byteArrayOutputStream.toString().trim();
        assertEquals(testData, result);
    }

    @Test
    void testStartVehicle() {
        car.startVehicle();
        String testData = "starting";
        String result = byteArrayOutputStream.toString().trim();
        assertEquals(testData, result);
    }
}
