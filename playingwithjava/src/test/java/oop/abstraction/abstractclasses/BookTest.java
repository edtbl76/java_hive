package oop.abstraction.abstractclasses;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {
    Book book = new Book("Le Morte D'Arthur", "Sir Thomas Mallory") {
        @Override
        public String getDetails() {
            return "";
        }
    };

    @ParameterizedTest
    @ValueSource(strings = {"Eight Men Out", "Do Androids Dream of Electric Sheep?", "Breakfast of Champions"})
    void testSetName(String bookTitle) {
        book.setName(bookTitle);
        assertEquals(bookTitle, book.getName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Eliot Asinof", "Philip K. Dick", "Kurt Vonnegut"})
    void testSetAuthor(String author) {
        book.setAuthor(author);
        assertEquals(author, book.getAuthor());
    }
}
