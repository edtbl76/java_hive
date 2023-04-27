package oop.abstraction.interfaces.solvingdiamondproblem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiamondDriverTest {
    final DiamondDriver diamondDriver = new DiamondDriver();
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
    void testPrint() {
        diamondDriver.print();
        String testData = "Driver\nInterface One\nInterface Two";
        assertEquals(testData, byteArrayOutputStream.toString().trim());
    }
}
