package structures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EMLinkedListTest {

    private EMLinkedList emLinkedListUnderTest;

    @BeforeEach
    void setUp() {
        emLinkedListUnderTest = new EMLinkedList();
    }

    @Test
    void testContains() {
        assertFalse(emLinkedListUnderTest.contains(4));

        emLinkedListUnderTest.insert(4);
        assertTrue(emLinkedListUnderTest.contains(4));

        emLinkedListUnderTest.insert(5).insert(6);
        assertTrue(emLinkedListUnderTest.contains(6));
    }



    @Test
    void testInsertByNode() {
        // Setup
        final LLNode LLNode = new LLNode(0);
        final EMLinkedList expectedResult = new EMLinkedList(0);

        // Run the test
        final EMLinkedList result = emLinkedListUnderTest.insert(LLNode);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testInsertAtHeadByNode() {
        emLinkedListUnderTest
                .insert(2)
                .insert(1);

        final EMLinkedList result = emLinkedListUnderTest.insertAtHead(new LLNode(3));
        int headValue = result.getHead().getValue();
        int nextValue = result.getHead().getNext().getValue();
        assertEquals(3, headValue);
        assertEquals(2, nextValue);
    }

    @Test
    void testInsertAtHeadByValue() {
        emLinkedListUnderTest
                .insert(2)
                .insert(1);

        final EMLinkedList result = emLinkedListUnderTest.insertAtHead(3);
        int headValue = result.getHead().getValue();
        int nextValue = result.getHead().getNext().getValue();
        assertEquals(3, headValue);
        assertEquals(2, nextValue);
    }

    @Test
    void testInsertAtEmptyHeadByNode() {
        final EMLinkedList result = emLinkedListUnderTest.insertAtHead(new LLNode(5));
        assertEquals(5, result.getHead().getValue());
    }

    @Test
    void testInsertAtEmptyHeadByValue() {
        final EMLinkedList result = emLinkedListUnderTest.insertAtHead(5);
        assertEquals(5, result.getHead().getValue());
    }

    @Test
    void testInsertAtEmptyNextByNode() {
        emLinkedListUnderTest.insert(1).insertAtHead(new LLNode(0));
        assertEquals(0, emLinkedListUnderTest.getHead().getValue());
    }

    @Test
    void testInsertAtEmptyNextByValue() {
        emLinkedListUnderTest.insert(1).insertAtHead(0);
        assertEquals(0, emLinkedListUnderTest.getHead().getValue());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void testInsertByValue(int data) {
        // Setup
        final EMLinkedList expectedResult = new EMLinkedList(data);

        // Run the test
        final EMLinkedList result = emLinkedListUnderTest.insert(data);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testDeleteByValueEmptyList() {
       assertEquals(new EMLinkedList(), emLinkedListUnderTest.deleteByValue(1));
    }

    @Test
    void testDeleteByIndexEmptyList() {
        assertEquals(new EMLinkedList(), emLinkedListUnderTest.deleteByIndex(1));
    }

    @Test
    void testDeleteByValueAtHead() {
        final EMLinkedList expectedResult = new EMLinkedList(null);
        emLinkedListUnderTest.insert(5);
        assertEquals(expectedResult, emLinkedListUnderTest.deleteByValue(5));
    }

    @Test
    void testDeleteByIndexAtHead() {
        final EMLinkedList expectedResult = new EMLinkedList(null);
        emLinkedListUnderTest.insert(5);
        assertEquals(expectedResult, emLinkedListUnderTest.deleteByIndex(0));
    }

    @Test
    void testDeleteByValueNotFound() {
        emLinkedListUnderTest = new EMLinkedList(8);
        assertEquals(new EMLinkedList(8), emLinkedListUnderTest.deleteByValue(9));
    }

    @Test
    void testDeleteByIndexNotFound() {
        emLinkedListUnderTest = new EMLinkedList(8);
        assertEquals(new EMLinkedList(8), emLinkedListUnderTest.deleteByIndex(2));
    }

    @Test
    void testDeleteByValue() {
        // Setup
        final EMLinkedList expectedResult = new EMLinkedList(0);
        expectedResult
                .insert(1)
                .insert(2);


        emLinkedListUnderTest
                .insert(0)
                .insert(1)
                .insert(2)
                .insert(3);

        final EMLinkedList result = emLinkedListUnderTest.deleteByValue(3);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testDeleteByIndex() {
        // Setup
        final EMLinkedList expectedResult = new EMLinkedList(0);

        expectedResult
                .insert(1)
                .insert(2);


        emLinkedListUnderTest
                .insert(0)
                .insert(1)
                .insert(2)
                .insert(3);

        final EMLinkedList result = emLinkedListUnderTest.deleteByIndex(3);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testEqualsDefaultConstructor() {
        assertEquals(emLinkedListUnderTest, new EMLinkedList());
    }

    @Test
    void testEqualsNodeConstructor() {
        emLinkedListUnderTest = new EMLinkedList(1);
        assertEquals(emLinkedListUnderTest, new EMLinkedList(1));
    }

    @Test
    void testEqualsDataConstructor() {
        emLinkedListUnderTest = new EMLinkedList(new LLNode(2));
        assertEquals(emLinkedListUnderTest, new EMLinkedList(new LLNode(2)));
    }

    @Test
    void testHashCode() {
        assertEquals(new EMLinkedList().hashCode(), emLinkedListUnderTest.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("EMLinkedList(head=null)", emLinkedListUnderTest.toString());
    }

}
