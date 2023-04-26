package com.strings.SearchFindAndSort;

import java.util.*;

public class FindDuplicateWordsInJavaString {

    public static void main(String[] args) {

        // Take some text.
        String text = "a r a g we f jh a s f bh AS D F JA D D s xv hg";

        // parse it up using a delimiter/regex.
        List<String> list = Arrays.asList(text.split(" "));

        // create a HashSet from the list so that we have unique words.
        Set<String> uniqueWords = new HashSet<>(list);

        // iterate through the HashSet and for each unique word, we pass it to Collections.frequency() against the original list to
        // determine how many instance of each word there are.
        for (String word : uniqueWords) {
            System.out.println(word + ": " + Collections.frequency(list, word));
        }
    }
}
