package oop.composition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    private Car carUnderTest;

    @BeforeEach
    void setUp() {
        carUnderTest = new Car("Toyota", "Highlander");
    }

    @Test
    void testEquals() {
        assertEquals(carUnderTest, new Car("Toyota", "Highlander"));
    }

    @Test
    void testCanEqual() {
        assertEquals(carUnderTest, new Car("Toyota", "Highlander"));
    }

    @Test
    void testHashCode() {
        assertEquals(
                new Car("Toyota", "Highlander").hashCode(),
                carUnderTest.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(
                new Car("Toyota", "Highlander").toString(),
                carUnderTest.toString());
    }

    @Test
    void testSetEngine() {
        Engine engine = new Engine();
        carUnderTest.setEngine(engine);
        assertEquals(engine, carUnderTest.getEngine());
    }

    @Test
    void testSetTires() {
        Tires tires = new Tires();
        carUnderTest.setTires(tires);
        assertEquals(tires, carUnderTest.getTires());
    }

    @Test
    void testSetDoors() {
        Doors doors = new Doors();
        carUnderTest.setDoors(doors);
        assertEquals(doors, carUnderTest.getDoors());
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8})
    void testSetEngine(int cylinders) {
        carUnderTest.setEngine(cylinders);
        assertEquals(new Engine(cylinders), carUnderTest.getEngine());
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 6})
    void testSetTires(int numberOfTires) {
        carUnderTest.setTires(numberOfTires);
        assertEquals(new Tires(numberOfTires), carUnderTest.getTires());

    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 5})
    void testSetDoors(int numberOfDoors) {
        carUnderTest.setDoors(numberOfDoors);
        assertEquals(new Doors(numberOfDoors), carUnderTest.getDoors());
    }



}
