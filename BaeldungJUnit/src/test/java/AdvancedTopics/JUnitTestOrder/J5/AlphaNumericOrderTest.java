package AdvancedTopics.JUnitTestOrder.J5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class AlphaNumericOrderTest {
    private static StringBuilder output = new StringBuilder("");

    @Test
    public void testA() {
        output.append("A");
    }

    @Test
    public void testB() {
        output.append("B");
    }

    /*
        LowerCase comes after UpperCase
     */
    @Test
    public void testa() {
        output.append("a");
    }

    @AfterAll
    public static void assertOutput() {
        assertEquals("ABa", output.toString());
    }
}
