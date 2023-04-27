package oop.composition;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tires {

    private int count;

    public Tires() {
        this.count = 0;
    }
}
