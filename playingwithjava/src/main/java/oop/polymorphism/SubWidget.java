package oop.polymorphism;

public class SubWidget extends Widget {

    private final String name;
    public SubWidget(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return super.getName() + ", " + this.name;
    }
}
