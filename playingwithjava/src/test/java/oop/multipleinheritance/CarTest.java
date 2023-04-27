package oop.multipleinheritance;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarTest {
    final Car car = new Car("make", "model");
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
    void testPrintDetails() {
        car.printDetails();
        String testData = "The model of " + car.getClass().getSimpleName() + " is: model\n" +
                "The make of " + car.getClass().getSimpleName() + " is: make";
        assertEquals(testData, byteArrayOutputStream.toString().trim());
    }

    @Test
    void testSetModel() {
        car.setModel("Barracuda");
        assertEquals("Barracuda", car.getModel());
    }

    @Test
    void testSetMake() {
        car.setMake("Plymouth");
        assertEquals("Plymouth", car.getMake());
    }


}
