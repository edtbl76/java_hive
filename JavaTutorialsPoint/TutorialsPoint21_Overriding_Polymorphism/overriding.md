# OVERRIDING 

    - If a class inherits a method from its parent, it can override it (assuming the 
    parent method() isn't marked as 'final'
        (final means it can't be changed)
        
    BENEFITS
    - ability to define a behavior that's specific to the subclass type, which means
    that a subclass can implement a parent class method based on its requirement
    
    In terms of OOP:
        "OVERRIDING" means to 'override the functionality of an exisitng method'
        
### HOW IT WORKS

    - at compile time a reference check is made, but in the RUNTIME, JVM 
    determines the method that belongs to a specific object. 
    
    Overriding is DYNAMIC or non-static Polymorphism.
    
    - compile is always successful because the methods exist. 
    - at runtime, the method is executed specific to the object. 