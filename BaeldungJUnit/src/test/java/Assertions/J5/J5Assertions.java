package Assertions.J5;


import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import javax.naming.OperationNotSupportedException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

public class J5Assertions {

    // **** assertArrayEquals() ****
    @Test
    public void whenAssertingArraysEquality_thenEqual() {
        char[] expected = {'a', 'r', 'r', 'a', 'y'};
        char[] actual = "array".toCharArray();
        assertArrayEquals(expected, actual, "Arrays should be equal");
    }

    public void whenAssertingArraysEquality_thenEqualFails() {
        char[] expected = {'a', 'r', 'r', 'a', 'y'};
        char[] actual = "not hot dog".toCharArray();
        assertArrayEquals(expected, actual, "Arrays should be equal");
    }

    // **** assertEquals() ****
    @Test
    public void whenAssertingEquality_thenEqual() {
        String expected = "string";
        String actual = "string";
        assertEquals(expected, actual);
    }

    @Test
    public void whenAssertingEqualityWithDelta_thenEqualSucceeds() {
        double square = 2 * 2;
        double rectangle = 3 * 2;
        double delta = 2;
        assertEquals(square, rectangle, delta);
    }

    @Test
    public void whenAssertingEqualityWithDelta_thenEqualFails() {
        double square = 2 * 2;
        double rectangle = 3 * 2;
        double delta = 0;
        assertEquals(square, rectangle, delta);
    }

    // **** assertTrue() and assertFalse() ****
    @Test
    public void whenAssertingConditions_thenVerified() {
        assertTrue(5 > 4, "5 is greater than 4");
        assertFalse(5 < 4, "5 is not less than 4");
    }

    @Test
    public void givenBooleanSupplier_whenAssertingCondition_thenVerified() {
        BooleanSupplier conditionTrue = () -> 5 > 4;
        BooleanSupplier conditionFalse = () -> 5 < 4;
        assertTrue(conditionTrue, "5 is greater than 4");
        assertFalse(conditionFalse, "5 is not less than 4");
    }

    // **** assertNotNull() and assertNull() ****
    @Test
    public void whenAssertingNotNull_thenTrue() {
        String string = "exists";
        assertNotNull(string, "String should not be null");
    }

    @Test
    public void whenAssertingNull_thenTrue() {
        String string = null;
        assertNull(string, "String should be null" );
    }

    // **** assertNotSame() and assertSame() ****
    @Test
    public void whenAssertingNotSame_thenDifferent() {
        Object object = new Object();
        Object otherObject = new Object();
        assertNotSame(object, otherObject);
    }

    @Test
    public void whenAssertingSame_thenSame() {
        Object object = new Object();
        assertSame(object, Optional.of(object).get());
    }



    // **** fail() ****
    public void failMethod() throws OperationNotSupportedException {}
    public void throwsMethod() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Operation Not Supported");
    }

    @Test
    public void whenCheckingExceptionMessage_thenEqualFails() {
        try {
            failMethod();
            fail("ExceptionNotThrown");
        } catch (OperationNotSupportedException e) {
            assertEquals("Operation Not Supported", e.getMessage());
        }
    }

    @Test
    public void whenCheckingExceptionMessage_thenEqualSucceeds() {
        try {
            throwsMethod();
            fail("ExceptionNotThrown");
        } catch (OperationNotSupportedException e) {
            assertEquals("Operation Not Supported", e.getMessage());
        }
    }

    // **** assertAll() ****
    @Test
    public void givenMultipleAssertion_whenAssertingAll_thenPass() {
        assertAll("heading",
                () -> assertEquals(4, 2 + 2, "2 + 2 = 4"),
                () -> assertEquals("string", "STRING".toLowerCase()),
                () -> assertEquals((Byte) null, null, "null = null")
        );
    }

    // **** assertIterableEquals ****
    @Test
    public void givenTwoLists_whenAssertingIterables_thenEquals() {
        Iterable<Integer> arrayList = Lists.newArrayList(1, 2, 3, 4, 5);
        Iterable<Integer> linkedList = Lists.newLinkedList(Arrays.asList(1, 2, 3, 4, 5));

        assertIterableEquals(arrayList, linkedList);
    }


    // **** assertLinesMatch() ****
    /*
       this matches line/element pairs as follows
       1.) if lines are equal, move to next
       2.) expected line is treated as regex and checks actual using String.matches()
       3.) if expected line is a fast-forward marker, apply fast-forward and repeat from step 1

     */
    @Test
    public void whenAssertingEqualityListOfStrings_thenEqual() {
        List<String> expected = Lists.newArrayList("Java", "\\d+", "JUnit");
        List<String> actual = Lists.newArrayList("Java", "11", "JUnit");

        assertLinesMatch(expected, actual);
    }

    // **** assertNotEquals() ****
    /*
        Used to ensure that a result doesn't happen
     */
    @Test
    public void whenAssertingEquality_thenNotEqual() {
        Integer value = 10; // result of some operation
        assertNotEquals(0, value, "Result should not be 0");
    }


    // **** assertThrows() ****
    @Test
    public void whenAssertingException_thenThrown() {
        Throwable throwable = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    throw new IllegalArgumentException("Stop Arguing!");
                }
        );
        assertEquals("Stop Arguing!", throwable.getMessage());
    }


    // **** assertTimeout() and assertTimeoutPreemptively() ****

    /*
        assertTimeout doesn't abort the execution if the timeout is exceeded.
        - if you want to abort execution use assertTimeoutPreemptively()
     */
    @Test
    public void whenAssertingTimeout_thenNotExceeded() {
        assertTimeout(
                Duration.ofSeconds(4),
                () -> {
                    // code that takes less than 4 seconds to complete
                    Thread.sleep(1000);
                }
        );
    }

    @Test
    public void whenAssertingTimeoutPE_thenNotExceeded() {
        assertTimeoutPreemptively(
                Duration.ofSeconds(4),
                () -> {
                    // code that takes less than 4 seconds to complete
                    Thread.sleep(1000);
                }
        );
    }

}
