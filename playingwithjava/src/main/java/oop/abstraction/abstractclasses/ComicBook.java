package oop.abstraction.abstractclasses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComicBook extends Book {

    private String artist;

    public ComicBook(String name, String author, String artist) {
        super(name, author);
        this.artist = artist;
    }

    @Override
    public String getDetails() {
        return "Title: " + this.name + "\nWriter: " + this.author + "\nArtist" + this.artist;
    }
}
