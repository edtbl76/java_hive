package oop.aggregation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountryTest {
    Country country = new Country("United States", 331_000_000);

    @ParameterizedTest
    @ValueSource(strings = {"Denmark", "Norway", "Sweden", "Finland", "Iceland"})
    void testSetName(String scandinavianCountry) {
        country.setName(scandinavianCountry);
        assertEquals(scandinavianCountry, country.getName());
    }

    @ParameterizedTest
    @ValueSource(ints = {5_870_000, 5_430_000, 10_450_000, 5_550_000, 380_000})
    void testSetPopulation(int population) {
        country.setPopulation(population);
        assertEquals(population, country.getPopulation());
    }
}
