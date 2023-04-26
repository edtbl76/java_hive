# INTERFACES

    - a reference type in Java similar to a class. 
    
    - it is a collection of abstract methods.
        - a class IMPLEMENTS an interface, thereby inheriting the 
        abstract methods of the interface. 
        
    - May contain
        - abstract methods
        - constants
        - default methods
        - static methods
        - nested types
        
    - Method bodies exist ONLY for
        - default methods
        - statuc methodes. 
        
    CLASS VS INTERFACE
        - a Class describes the attributes(via fields/nouns) and behaviors
        (via methods/verbs)
        
        - an interface contains behaviors that another class implements.
        
        - Unless the class that implements the interface is abstract, 
        ALL the methods of the interface need to be defined in the class. 
        
        
    SIMILARITIES
        - an interface has no limit to the number of methods
        - an interface is written in a file w/ a .java extension, w/ the same
        name constraints (name must be the same case-specific name as class or
        interface) 
        - The byte code of an interface appears in a .class file
        - interfaces appear in packages, and their corresponding bytecode file
        must be in a directory structure that matches the package name. 
        
    DIFFERENCES
        - you can not instantiate an interface (only implement it) 
        - an interface doesn't contain any constructors 
        - ALL of the mehtods in an interface are abstract
        - an interface cannot contain instance fields
            - the only FIELDS that can appear in an interface MUST be
            declared both 'STATIC" and 'FINAL'
            
        - an interface is not extended by the class, it is 
        IMPLEMENTED by a class. 
        - an interface can extend MULTIPLE INTERFACES.
        
### Declaring Interfaces

    - an interface is declared using the 'interface' keyword
    
    EX:
        public interface NameOfInterface {
            // any number of static final fields
            // any number of abstract method declarations.
        }
        
### PROPERTIES OF INTERFACES

    - an interface is IMPLICITLY ABSTRACT
        - DO NOT USE THE 'abstract' KEYWORD while declaring an interface
        
    - each method in an interface is IMPLICITLY ABSTRACT
        - DO NOT USE THE 'abstract' KEYWORD while declaring methods in an
        interface
        
    - methods in an interface are IMPLICITLY PUBLIC
        - DO NOT ADD THE PUBLIC KEYWORD TO methods in an interface. 
        
        
        EX:
        
        interface Animal
            public void eat();
            public void travel();
            
### IMPLEMENTING INTERFACES

    - when a class implements an interface, you can think of the class as 
    SIGNING a CONTRACT
        - agreeing to perform the specific behaviors of the interface. 
        
    - if a class does NOT perform ALL of the behaviors of the interface it must
    declare itself as ABSTRACT
    
    
    - a class may implement more than one interface at a time
    
    - a class can extend only one class, but implement MANY interfaces
    
    - an interface can extend another interface, in a similar way as a class 
    can extend another class. 
    
### EXTENDING INTERFACES

    - interface can extend another interface in the SAME way that 
    a class can extend another class.
    
    - the big difference between classes and interface is that while 
    classes can only extend a single class, interfaces support
    multiple inheritance.
    
        EX: 
        
            interface MultipleInheritance extends DadInterfaceOne, DadInterfaceTwo
            
### TAGGING INTERFACES

    - this is the most common use case for multiple inheritance in interfaces. 
    - this occurs when the parent interface contains ZERO METHODS
    
    - TAGGED INTERFACE
        - this is an interface that has no methods. 
        
        TWO USE CASES
        1.) CREATE A COMMON PARENT
            - this is often desirable for the purpose of creating groups of
            other interfaces for the purposes of organization and code
            management. 
            
        2.) ADDS A DATA TYPE TO A CLASS
            - this is where the term 'tagging' actually comes from. 
            - a class that iimplements a tagging interface doesn't need to 
            define any methods (since the tagged interface won't have any), but
            the CLASS itself becomes an interface 'type' via 
            polymorphism.
            
       
     
