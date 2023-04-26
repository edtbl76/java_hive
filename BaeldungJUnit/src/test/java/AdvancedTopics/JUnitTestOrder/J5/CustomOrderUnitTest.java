package AdvancedTopics.JUnitTestOrder.J5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(CustomOrder.class)
public class CustomOrderUnitTest {
    private static StringBuilder output = new StringBuilder("");

    @Test
    public void testA() {
        output.append("A");
    }

    @Test
    public void testa() {
        output.append("a");
    }

    @Test
    public void testB() {
       output.append("B");
    }

    @AfterAll
    public static void assertOutput() {
        assertEquals("AaB", output.toString());
    }

}
