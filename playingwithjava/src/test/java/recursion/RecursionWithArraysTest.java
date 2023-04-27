package recursion;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static recursion.RecursionWithArrays.*;

class RecursionWithArraysTest {

    @Test
    void testFirstIndexOfIsNotFound() {
        int[] empty = new int[]{};
        assertEquals(-1 , firstIndexOf(empty, 1, 0));
    }

    @Test
    void testFirstIndexOfIsFound() {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertEquals(7, firstIndexOf(array, 8, 0));
    }

    @Test
    void testInvertArrayOuter() {
        int[] array = new int[]{1, 2, 3, 4, 5};
        int[] expected = new int[]{5, 4, 3, 2, 1};
        invertArrayElements(array, 0);
        assertArrayEquals(array, expected);
    }

    @Test
    void testInvertArrayInner() {
        int[] array = new int[]{1, 2, 3, 4, 5};
        int[] expected = new int[]{1, 4, 3, 2, 5};
        invertArrayElements(array, 1);
        assertArrayEquals(array, expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4})
    void testInvertArrayDoesNotChange(int index) {
        int[] array = new int[]{1, 2, 3, 4, 5};
        int[] expected = new int[]{1, 2, 3, 4, 5};
        invertArrayElements(array, index);
        assertArrayEquals(array, expected);
    }

    @Test
    void testSumArray() {
        int[] array = new int[]{1, 2, 3, 4, 5};
        assertEquals(15, sumArray(array, array.length));
    }
}
