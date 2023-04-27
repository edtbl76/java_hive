package oop.aggregation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.openMocks;

class PersonTest {
    @Mock
    Country country;
    @InjectMocks
    Person person;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Mr. Tumnus"})
    void testSetName(String name) {
        person.setName(name);
        assertEquals(name, person.getName());
    }

    @Test
    void testSetCountry() {
        country = new Country("Narnia", 1);
        person.setCountry(country);
        assertEquals(country, person.getCountry());
    }
}
