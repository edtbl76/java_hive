package oop.abstraction.abstractclasses;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ComicBookTest {
    ComicBook comicBook = new ComicBook("Kingdom Come", "Mark Waid", "Alex Ross");

    @Test
    void testGetDetails() {
        String result = comicBook.getDetails();
        String testData = "Title: " + comicBook.getName()
                + "\nWriter: " + comicBook.getAuthor()
                + "\nArtist" + comicBook.getArtist();
        assertEquals(testData, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"The Dark Knight Returns, Watchmen, The Crow"})
    void testSetName(String title) {
        comicBook.setName(title);
        assertEquals(title, comicBook.getName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Frank Miller", "Alan Moore", "James O'Barr"})
    void testSetAuthor(String writer) {
        comicBook.setAuthor(writer);
        assertEquals(writer, comicBook.getAuthor());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Klaus Janson", "Dave Gibbons", "James O'Barr"})
    void testSetArtist(String artist) {
        comicBook.setArtist(artist);
        assertEquals(artist, comicBook.getArtist());
    }




}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme