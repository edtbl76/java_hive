package structures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    private BinarySearchTree binarySearchTreeUnderTest;

    @BeforeEach
    void setUp() {
        binarySearchTreeUnderTest = new BinarySearchTree();
    }

    @Test
    void testMin() {
        binarySearchTreeUnderTest = new BinarySearchTree(3);
        assertEquals(3, binarySearchTreeUnderTest.min());

        binarySearchTreeUnderTest.insert(5);
        assertEquals(3, binarySearchTreeUnderTest.min());

        binarySearchTreeUnderTest.insert(1);
        assertEquals(1, binarySearchTreeUnderTest.min());
    }

    @Test
    void testIsEmpty() {
        assertTrue(binarySearchTreeUnderTest.isEmpty());

        binarySearchTreeUnderTest.insert(0);
        assertFalse(binarySearchTreeUnderTest.isEmpty());
    }

    @Test
    void testInsert() {
        binarySearchTreeUnderTest.insert(2);
        assertEquals(2, binarySearchTreeUnderTest.getRoot().getKey());

        // test insert same value
        binarySearchTreeUnderTest.insert(2);
        assertEquals(2, binarySearchTreeUnderTest.getRoot().getKey());

        binarySearchTreeUnderTest.insert(1);
        assertEquals(1, binarySearchTreeUnderTest.getRoot().getLeft().getKey());

        binarySearchTreeUnderTest.insert(3);
        assertEquals(3, binarySearchTreeUnderTest.getRoot().getRight().getKey());
    }

    @Test
    void testDeleteKeyNullRoot() {

        // Test null root
        binarySearchTreeUnderTest.deleteKey(1);
        assertNull(binarySearchTreeUnderTest.getRoot());

        // Set root and delete it
        binarySearchTreeUnderTest.insert(1);
        binarySearchTreeUnderTest.deleteKey(1);
        assertNull(binarySearchTreeUnderTest.getRoot());
    }

    @Test
    void testDeleteKeyBottomUp() {

        // Setup a traversal 2 = Root, 1 = left, 3 = right
        binarySearchTreeUnderTest.insert(2);
        binarySearchTreeUnderTest.insert(1);
        binarySearchTreeUnderTest.insert(3);

        // Delete left branch
        binarySearchTreeUnderTest.deleteKey(1);
        assertNull(binarySearchTreeUnderTest.getRoot().getLeft());

        // Delete Right branch
        binarySearchTreeUnderTest.deleteKey(3);
        assertNull(binarySearchTreeUnderTest.getRoot().getRight());

        // Delete Root
        binarySearchTreeUnderTest.deleteKey(2);
        assertNull(binarySearchTreeUnderTest.getRoot());
    }

    @Test
    void testDeleteKeyTopDown() {
        // Setup a traversal 2 = Root, 1 = left, 3 = right
        binarySearchTreeUnderTest.insert(2);
        binarySearchTreeUnderTest.insert(1);
        binarySearchTreeUnderTest.insert(3);

        // Delete Root, which moves 3 to root and leaves right as null
        binarySearchTreeUnderTest.deleteKey(2);
        assertEquals(3, binarySearchTreeUnderTest.getRoot().getKey());

        // Delete Root which moves 1 to root w/o children
        binarySearchTreeUnderTest.deleteKey(3);
        assertEquals(1, binarySearchTreeUnderTest.getRoot().getKey());
    }

    @Test
    void testSearchNullRoot() {
        assertFalse(binarySearchTreeUnderTest.search(1));
    }

    @Test
    void testSearch() {
        // Setup a traversal 2 = Root, 1 = left, 3 = right
        binarySearchTreeUnderTest.insert(2);
        binarySearchTreeUnderTest.insert(1);
        binarySearchTreeUnderTest.insert(3);
        assertTrue(binarySearchTreeUnderTest.search(1));
        assertTrue(binarySearchTreeUnderTest.search(3));
    }


    @Test
    void testEquals() {
        assertEquals(new BinarySearchTree(), binarySearchTreeUnderTest);

        binarySearchTreeUnderTest = new BinarySearchTree(1);
        assertEquals(new BinarySearchTree(1), binarySearchTreeUnderTest);

        binarySearchTreeUnderTest = new BinarySearchTree(new BSTNode(1));
        assertEquals(new BinarySearchTree(1), binarySearchTreeUnderTest);


    }

    @Test
    void testHashCode() {
        assertEquals(new BinarySearchTree().hashCode(), binarySearchTreeUnderTest.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("BinarySearchTree(root=null)", binarySearchTreeUnderTest.toString());
    }
}
