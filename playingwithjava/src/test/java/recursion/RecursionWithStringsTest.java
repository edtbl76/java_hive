package recursion;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static recursion.RecursionWithStrings.reverseString;

class RecursionWithStringsTest {


    @ParameterizedTest
    @CsvSource(value = {"'':''", "hello:olleh", "Hello world!:!dlrow olleH"}, delimiter = ':')
    void testReverseString(String input, String expected) {
        assertEquals(expected, reverseString(input));
    }

    @ParameterizedTest
    @CsvSource(value={"'':''", "a:a", "Hello:Helo", "aaaaaaa:a"}, delimiter = ':')
    void testRemoveDuplicates(String input, String expected) {
        assertEquals(expected, RecursionWithStrings.removeDuplicates(input));
    }

    @Test
    void testPermutationsTwoChars() {
        String example = "ab";
        List<String> testList = List.of("ab", "ba");
        assertEquals(testList, RecursionWithStrings.permutations(example));
    }

    @Test
    void testPermutationsThreeChars() {
        String example = "abc";
        List<String> testList = List.of("abc", "bac", "bca", "acb", "cab", "cba");
        assertEquals(testList, RecursionWithStrings.permutations(example));
    }

    @ParameterizedTest
    @CsvSource(value = {"'':0", "ghjk:0", "hello:2", "Banana:3", "onomatopoeia:8"}, delimiter = ':')
    void testCountVowels(String input, String expected) {
        assertEquals(parseInt(expected), RecursionWithStrings.countVowels(input));
    }
}

