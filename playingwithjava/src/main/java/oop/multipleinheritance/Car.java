package oop.multipleinheritance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Car {

    private String make;
    private String model;

    @SuppressWarnings("java:S106")
    public void printDetails() {
        System.out.println("The model of " + getClass().getSimpleName() + " is: " + this.model);
        System.out.println("The make of " + getClass().getSimpleName() + " is: " + this.make);
    }
}
