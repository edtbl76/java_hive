package oop.composition;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Engine {

    private int cylinders;

    public Engine() {
        this.cylinders = 4;
    }
}
