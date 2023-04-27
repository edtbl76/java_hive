package oop.multipleinheritance;

import lombok.Getter;
import lombok.Setter;


@SuppressWarnings("java:S106")
@Getter
@Setter
public class Plymouth extends Car implements IsCoupe {

    private String carPackage;
    public Plymouth(String model, String carPackage) {
        super("Plymouth", model);
        this.carPackage = carPackage;
    }

    @Override
    public void openDoors() {
        System.out.println(IsCoupe.DOOR_COUNT + " only. Shotgun!");
    }


    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("The package of " + getClass().getSimpleName() + " is: " + this.carPackage);
    }
}
