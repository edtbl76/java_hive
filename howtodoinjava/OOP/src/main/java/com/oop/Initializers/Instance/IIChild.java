package com.oop.Initializers.Instance;

public class IIChild extends IIParent {

    public IIChild() {
        super();        // JVM will insert this if we don't.
        System.out.println("In IIChild Constructor");
    }

    // Instance Initializer 1
    {
        System.out.println("In IIChild Instance Initializer 1");
    }

    // Instance Initializer 2
    {
        System.out.println("In IIChild Instance Initializer 2");
    }

}
