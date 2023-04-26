package com.oop.Inheritance;

public class ParentMethodsExample {

    public static void main(String[] args) {


        /*
            Check out ParentFields.
            - Java DOES allow method overrides, so it derives the member method based on the ACTUAL class.

            ReferenceClass variable = new ActualClass();
         */
        PMEBase base = new PMEBase();
        PMEBase notBase = new PMEDerived();
        PMEDerived derived = new PMEDerived();

        System.out.println(base.getId());       // 10
        System.out.println(notBase.getId());    // 20
        System.out.println(derived.getId());    // 20

    }

}

class PMEBase {
    private Long id = 10L;

    public Long getId() {
        return id;
    }
}

class PMEDerived extends  PMEBase{
    private Long id = 20L;

    public Long getId() {
        return id;
    }
}
