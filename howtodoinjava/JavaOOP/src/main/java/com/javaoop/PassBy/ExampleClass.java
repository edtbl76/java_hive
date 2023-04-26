package com.javaoop.PassBy;

public class ExampleClass {

    private String attribute;

    public ExampleClass (String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
