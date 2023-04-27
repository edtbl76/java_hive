package oop.polymorphism;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class Rectangle extends Shape {

    private double width;
    private double height;
    @Override
    public double getArea() {
        return this.width * this.height;
    }
}
