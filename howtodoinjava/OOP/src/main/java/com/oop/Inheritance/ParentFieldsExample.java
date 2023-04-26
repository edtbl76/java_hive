package com.oop.Inheritance;

public class ParentFieldsExample {
    public static void main(String[] args) {

        /*
            This demonstrates that you can't override fields in java.
            In this case, the attribute we access is determined by the "REFERENCE CLASS"
            ReferenceClass variable = new ActualClass();
         */
        PFEBase base = new PFEBase();
        PFEBase notBase = new PFEDerived();
        PFEDerived derived = new PFEDerived();

        System.out.println(base.id);        // 10
        System.out.println(notBase.id);     // 10
        System.out.println(derived.id);     // 20
    }
}

class PFEBase {
    public Long id = 10L;
}

class PFEDerived extends PFEBase {
    public Long id = 20L;
}
