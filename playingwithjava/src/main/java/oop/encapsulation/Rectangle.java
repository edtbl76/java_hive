package oop.encapsulation;

public class Rectangle {

    private int length;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    private int width;

    public Rectangle() {
        this.length = 0;
        this.width = 0;
    }

    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getArea() {
        return this.length * this.width;
    }
}
