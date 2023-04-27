package oop.composition;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class Car {

    private Engine engine;
    private Tires tires;
    private Doors doors;
    private final String make;
    private final String model;

    public Car(String make, String model) {
        this.make = make;
        this.model = model;
        this.engine = new Engine();
        this.tires = new Tires();
        this.doors = new Doors();
    }


    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setEngine(int cylinders) {
        this.engine = new Engine(cylinders);
    }

    public void setTires(Tires tires) {
        this.tires = tires;
    }

    public void setTires(int numberOfTires) {
        this.tires = new Tires(numberOfTires);
    }

    public void setDoors(Doors doors) {
        this.doors = doors;
    }

    public void setDoors(int numberOfDoors) {
        this.doors = new Doors(numberOfDoors);
    }

}
