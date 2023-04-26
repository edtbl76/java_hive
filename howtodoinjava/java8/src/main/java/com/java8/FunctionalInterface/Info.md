# Functional Interfaces
- new Java 8 structure 
    - permits exactly ONE abstract method inside it
    - a.k.a. SAM (Single Abstract Method) interfaces
    
- may be represented as
    - Lambda Expressions
    - Method Reference
    - Constructor Reference
    
- @FunctionalInterface (annotation)
    - used for compiler level errors when interface annotated violates contracts of
    exactly  ONE ABSTRACT METHOD
    
## BEST PRACTICES
- only ONE ABSTRACT METHOD is allowed
    - if the annotation is removed, you can add additional abstract methods, but
    this makes the interface a NON FUNCTIONAL INTERFACE 
    
- a functional interface is valid even w/o the annotation
    - the annotation is only used for informing the compiler to enforce the 
    SAM rules inside the interface. 
    
- since DEFAULT METHODS have an implementation, they aren't abstract. 
    - since default methods are NOT abstract, you can add default methods to
    functional interfaces as many as you like. 
    
- if an interface declares an abstract method overriding one of the PUBLIC METHODS of
JAVA.LANG.OBJECT, iit also doesn't count towards the interface's abstract method count