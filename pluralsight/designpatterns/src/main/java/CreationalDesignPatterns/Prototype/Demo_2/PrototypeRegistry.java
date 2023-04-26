package CreationalDesignPatterns.Prototype.Demo_2;

import java.util.HashMap;
import java.util.Map;

public class PrototypeRegistry {
    private Map<String, Item> items = new HashMap<>();

    public PrototypeRegistry() {
        // populates Registry on first time instantiation.
        loadItems();
    }

    /*
        This is the heart and soul of the prototype pattern.

        the createItem() method
     */
    public Item createItem(String type) {
        Item item = null;

        /*
            If you look at the Item object, the clone() method requires that we return it as an Object.
            This forces us to cast the return value of clone() as (Item)
         */
        try {
            item = (Item)(items.get(type)).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return item;
    }

    /*
        These are template objects (movie and book).
        When we create new objects using createItem(), it is going to clone the template and override
        the template values created.

        This is the ONLY time the keyword new is used, which means that the instances we are getting back are
        constantly being changed.

        NOTE: we're using a String key in the HashMap, but a Smarter implementation would be to use an ENUM.

     */
    private void loadItems() {
        Movie movie = new Movie();
        movie.setTitle("Basic Movie");
        movie.setPrice(19.99);
        movie.setMovieLength("90 minutes");
        items.put("Movie", movie);

        Book book = new Book();
        book.setTitle("Basic Book");
        book.setPrice(24.99);
        book.setPageCount(595);
        items.put("Book", book);
    }
}
