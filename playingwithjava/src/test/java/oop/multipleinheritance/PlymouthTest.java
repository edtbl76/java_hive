package oop.multipleinheritance;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlymouthTest {
    Plymouth plymouth = new Plymouth("Barracuda", "Muscle");
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

    @ParameterizedTest
    @ValueSource(strings = {IsCoupe.DOOR_COUNT + " only. Shotgun!"})
    void testOpenDoors(String doorsOpen) {
        plymouth.openDoors();
        assertEquals(doorsOpen, byteArrayOutputStream.toString().trim());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Barracuda"})
    void testSetModel(String model) {
        plymouth.setModel(model);
        assertEquals(model, plymouth.getModel());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Muscle"})
    void testSetCarPackage(String carPackage) {
        plymouth.setCarPackage(carPackage);
        assertEquals(carPackage, plymouth.getCarPackage());
    }

    @Test
    void testPrintDetails() {
        plymouth.printDetails();
        String testData = "The model of " + plymouth.getClass().getSimpleName() + " is: " + plymouth.getModel() + "\n"
                + "The make of " + plymouth.getClass().getSimpleName() + " is: " + plymouth.getMake() + "\n"
                + "The package of " + plymouth.getClass().getSimpleName() + " is: " + plymouth.getCarPackage();
        assertEquals(testData, byteArrayOutputStream.toString().trim());
    }



}
