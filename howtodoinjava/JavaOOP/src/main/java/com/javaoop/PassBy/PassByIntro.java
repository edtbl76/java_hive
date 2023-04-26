package com.javaoop.PassBy;

public class PassByIntro {

    /*
        Java is a PassByValue language.

        When you pass an instance to a method, it's memory  addresses are copied bit-by-bit to a new reference var.

     */
    public static void main(String[] args) {
        /*
            We  are  creating a new instance of ExampleClass. example's attribute is initialized to "example"
         */
        ExampleClass example = new ExampleClass("example");
        System.out.println("Initial: " + example.getAttribute());

        // see function
        changeReference(example);
        System.out.println("After Change: " + example.getAttribute());  // no change.
        modifyReference(example);
        System.out.println("After Modify: " + example.getAttribute());
    }

    /*
        When this is compiled, the parameter ec is set to null.
        - when it is called (by changeReference(example)) above, ec is set to the value of the argument, so it points
        at the same instance as example in main()
        - then we assign ec to a new instance (of local scope)... This shadows the variable hiding the data
        that was assigned to it during
     */
    private static void changeReference(ExampleClass ec) {

        ec = new ExampleClass("change");
        System.out.println(ec.getAttribute());
    }

    private static void modifyReference(ExampleClass ec) {
        ec.setAttribute("modified");
    }

}
