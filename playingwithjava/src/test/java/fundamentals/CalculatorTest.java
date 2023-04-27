package fundamentals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator(36, 4);
    }

    @AfterEach
    void tearDown() {
        calculator = null;
    }

    @Test
    void testAdd() {
        double result = calculator.add();
        Assertions.assertEquals(40, result);
    }

    @Test
    void testSubtract() {
        double result = calculator.subtract();
        Assertions.assertEquals(32, result);
    }

    @Test
    void testMultiply() {
        double result = calculator.multiply();
        Assertions.assertEquals(144, result);
    }

    @Test
    void testDivide() {
        double result = calculator.divide();
        Assertions.assertEquals(9, result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme