package oop.abstraction.abstractclasses;

import com.google.common.base.CharMatcher;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SheepTest {

    final Sheep sheep = new Sheep();

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
    void testMakeSound() {
        sheep.makeSound();
        String testData = StringUtils.stripEnd(byteArrayOutputStream.toString(), null);
        assertEquals("Baa baa", testData);
    }

    @Test
    void testMove() {
        sheep.move();
        String testData = sheep.getClass().getSimpleName() + " is moving";
        // Guava is an alternative method from Apache Commons to trim from the right
        String result = CharMatcher.whitespace().trimTrailingFrom(byteArrayOutputStream.toString());
        assertEquals(testData, result);
    }
}
