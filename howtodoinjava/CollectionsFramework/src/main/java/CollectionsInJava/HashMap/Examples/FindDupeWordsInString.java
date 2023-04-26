package CollectionsInJava.HashMap.Examples;

import java.util.*;

public class FindDupeWordsInString {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        String text = "a r b k c d se f g a d f s s f d s ft gh f ws w f v x s g h d h j j k f sd j e wed a d f";

        List<String> list = Arrays.asList(text.split(" "));
        Set<String> uniqueWords = new HashSet<>(list);
        uniqueWords.forEach(s -> System.out.println(s + ": " + Collections.frequency(list, s)));
    }
}
