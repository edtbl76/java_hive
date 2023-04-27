package oop.polymorphism;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class Circle extends Shape {

    private double radius;

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }
}
