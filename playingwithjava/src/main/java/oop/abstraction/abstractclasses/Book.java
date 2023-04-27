package oop.abstraction.abstractclasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public abstract class Book {

    protected String name;
    protected String author;

    // Abstract Method
    public abstract String getDetails();

}
