package structures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTNodeTest {

    private BSTNode bstNodeUnderTest;

    @BeforeEach
    void setUp() {
        bstNodeUnderTest = new BSTNode(0);
    }

    @Test
    void testEquals() {
        assertEquals(bstNodeUnderTest, new BSTNode(0));
    }

    @Test
    void testHashCode() {
        assertEquals(new BSTNode(0).hashCode(), bstNodeUnderTest.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("BSTNode(key=0, left=null, right=null)", bstNodeUnderTest.toString());
    }

    @Test
    void testSetKey() {
        bstNodeUnderTest.setKey(5);
        assertEquals(5, bstNodeUnderTest.getKey());
    }
    @Test
    void testSetChildren() {
        bstNodeUnderTest.setKey(2);
        BSTNode left = new BSTNode(1);
        BSTNode right = new BSTNode(3);

        bstNodeUnderTest.setLeft(left);
        assertEquals(1, bstNodeUnderTest.getLeft().getKey());

        bstNodeUnderTest.setRight(right);
        assertEquals(3, bstNodeUnderTest.getRight().getKey());
    }
}
