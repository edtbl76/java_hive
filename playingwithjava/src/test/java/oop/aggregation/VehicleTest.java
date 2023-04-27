package oop.aggregation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleTest {
    Vehicle vehicle;

    @BeforeEach
    void setUp() {
        vehicle = new Vehicle("make", "model");
    }

    @AfterEach
    void tearDown() {
        vehicle =  null;
    }

    @ParameterizedTest
    @ValueSource(strings = {"Dodge", "Plymouth", "Chevrolet", "Ford"})
    void testSetMake(String make) {
        vehicle.setMake(make);
        assertEquals(make, vehicle.getMake());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Charger", "Barracuda", "Camaro", "Mustang"})
    void testSetModel(String model) {
        vehicle.setModel(model);
        assertEquals(model, vehicle.getModel());
    }
}
