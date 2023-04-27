package recursion;

import java.util.*;

public class RecursionWithStrings {

    static final List<Character> VOWELS = List.of('A', 'E', 'I', 'O', 'U');

    private RecursionWithStrings() {}

    public static String reverseString(String str) {

        // base case
        if (str.isEmpty()) {
            return str;
        }

        // recurse
        return reverseString(str.substring(1)) + str.charAt(0);
    }

    public static String removeDuplicates(String str) {

        // base
        if (str.isEmpty() || str.length() == 1) {
            return str;
        }

        // recurse
        // Maybe a smell? I like ternary statements.
        return (str.charAt(0) == str.charAt(1))
                ? removeDuplicates(str.substring(1))
                : str.charAt(0) + removeDuplicates(str.substring(1));

    }

    public static List<String> permutations(String str) {

        List<String> permutations = new ArrayList<>();
        if (str.length() <= 1) {
            permutations.add(str);
        } else {

            // chop off first character and isolate the remaining
            char prefix = str.charAt(0);
            String substring = str.substring(1);

            // store the remainder and iterate
            List<String> strings = permutations(substring);
            strings.forEach(s -> {
                for (int i = 0; i <= s.length(); i++ ) {
                    String inserted = insertCharAt(s, prefix, i);
                    permutations.add(inserted);
                }
            });

        }
        return permutations;

    }

    private static String insertCharAt(String str, char character, int index) {
        String head = str.substring(0, index);
        String tail = str.substring(index);
        return head + character + tail;
    }

    public static int countVowels(String text) {
        // Driver method to make it more user-friendly
        // We can derive the length from a string, and the index always starts at 0
        return countVowels(text, text.length(), 0);
    }

    private static int countVowels(String text, int length, int index) {

        // base
        if (length == 0) {
            return 0;
        }

        // counter modification
        int counter = 0;
        char character = Character.toUpperCase(text.charAt(index));
        if (VOWELS.contains(character)) {
            counter++;
        }

        // recursion
        return counter + countVowels(text, length - 1, index + 1);

    }
}
