package Assertions.J4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.naming.OperationNotSupportedException;

import java.util.Arrays;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class J4Assertions {

    // **** assertEquals() ****
    @Test
    public void whenAssertingEquality_thenEqual() {
        String expected = "string";
        String actual = "string";
        assertEquals(expected, actual);
    }

    @Test
    public void whenAssertingEquality_thenEqualOrFailureMessage() {
        String expected = "string";
        String actual = "not hot dog";
        assertEquals("FAIL: Strings are unequal", expected, actual);
    }

    // **** assertArrayEquals() ****
    @Test
    public void whenAssertingArraysEquality_thenEqual() {
        char[] expected = {'a', 'r', 'r', 'a', 'y'};
        char[] actual = "array".toCharArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void givenNullArrays_whenAssertingArraysEquality_thenEqual() {
        char[] expected = null;
        char[] actual = null;
        assertArrayEquals(expected, actual);
    }

    // **** assertNotNull() and assertNull() ****
    @Test
    public void whenAssertingNotNull_thenTrue() {
        String string = "exists";
        assertNotNull(string);
    }

    @Test
    public void whenAssertingNull_thenTrue() {
        String string = null;
        assertNull(string);
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
        Object reference = object;
        assertSame(object, reference);
    }

    // **** assertTrue() and assertFalse() ****
    @Test
    public void whenAssertingConditions_thenVerified() {
        assertTrue("5 is greater than 4",  5 > 4);
        assertFalse("5 is not less than 4", 5 < 4);
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

    // **** assertThat() ****

    /*
        two notes;
            1.) the comparison order is backwards (i.e. actual, THEN expected)
            2.) the hasItems() comes from Hamcrest library (look at the static import above)
     */
    @Test
    public void testAssertThatHasItems() {
        assertThat(
                Arrays.asList(1, 2, 3, 4),
                hasItems(1, 2, 3, 4)
        );
    }

}
