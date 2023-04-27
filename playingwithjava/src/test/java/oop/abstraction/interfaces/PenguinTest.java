package oop.abstraction.interfaces;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PenguinTest {

    final Penguin penguin = new Penguin();
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
    void waddle() {
        penguin.waddle();
        String testData = "Penguin is waddling.";
        String result = Utils.rightTrim(byteArrayOutputStream.toString());
        assertEquals(testData, result);
    }
}