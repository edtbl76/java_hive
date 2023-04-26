package AdvancedTopics.CustomTestRunnersJ4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(TestRunner.class)
public class CalcTest {
    Calculator calculator = new Calculator();

    @Test
    public void testAddition() {
        System.out.println("in testAddition");
        assertEquals("addition", 8, calculator.add(5, 3));
    }
}

class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
}
