package oop.aggregation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.*;

class DriverTest {
    @Mock
    Vehicle vehicle;
    @InjectMocks
    Driver driver;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Speed Racer", "Racer X"})
    void testSetName(String name) {
        driver.setName(name);
        assertEquals(name, driver.getName());
    }

    @Test
    void testSetVehicle() {
        vehicle = new Vehicle("Mifune","Mach 5");
        driver.setVehicle(vehicle);
        assertEquals(vehicle, driver.getVehicle());
    }
}
