package oop.abstraction.interfaces.solvingdiamondproblem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;

class InterfaceTwoTest {
    final InterfaceTwo interfaceTwo = spy(InterfaceTwo.class);

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
        interfaceTwo.print();
        assertEquals("Interface Two", byteArrayOutputStream.toString().trim());

    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme