package oop.abstraction.interfaces;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class BirdTest {
    final Bird bird = new Bird();

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
    void testEat() {
        bird.eat();
        String testData = bird.getClass().getSimpleName() + " is eating!";
        // My right trim method
        String result = Utils.rightTrim(byteArrayOutputStream.toString());
        Assertions.assertEquals(testData, result);
    }
}
