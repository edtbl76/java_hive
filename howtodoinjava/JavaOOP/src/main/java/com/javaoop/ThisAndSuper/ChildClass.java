package com.javaoop.ThisAndSuper;

public class ChildClass extends ParentClass {
    public void showMyName() {
        System.out.println("In ChildClass");
    }

    public void test() {
        this.showMyName();
        super.showMyName();
    }
}
