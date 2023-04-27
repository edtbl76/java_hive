package oop.composition;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Doors {

    private int count;

    public Doors() {
        this.count = 0;
    }
}
