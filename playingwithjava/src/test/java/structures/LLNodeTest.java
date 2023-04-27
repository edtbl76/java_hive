package structures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LLNodeTest {

    private LLNode LLNodeUnderTest;

    @BeforeEach
    void setUp() {
        LLNodeUnderTest = new LLNode(0);
    }

    @Test
    void testEquals() {
        assertEquals(LLNodeUnderTest, new LLNode(0));
    }

    @Test
    void testCanEqual() {
        assertTrue(LLNodeUnderTest.canEqual(new LLNode(0)));
    }

    @Test
    void testHashCode() {
        assertEquals(new LLNode(0).hashCode(), LLNodeUnderTest.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(new LLNode(0).toString(), LLNodeUnderTest.toString());
    }

    @Test
    void testSetValue() {
        LLNodeUnderTest.setValue(1);
        assertEquals(1, LLNodeUnderTest.getValue());
    }

    @Test
    void testSetNext() {
        LLNodeUnderTest.setNext(new LLNode(5));
        assertEquals(new LLNode(5), LLNodeUnderTest.getNext());
    }


}
