# Java Inheritance

    - the process where one class acquires properties (methods and fields) of another.
        - this makes information manageable in a hierarchical order. 
        
    subclass (derived class, child class) 
        - class that inherits properties. 
        
    superclass (base class, parent class) 
        - class whose properties are inherited


### extends and implements Keywords

    - extends is used in Java to inherit properties of a class. 
    
        class Dad { }
        
        class Son extends Dad { }
        
    - implements is used in Java to inherit properties of an Interface
    
        Interface Dad { }
        
        class Son implements Dad { }
        
     - what's the difference? 
        - a subclass can only extend ONE parent class
        - a subclass can implement MULTIPLE interfaces.
        
        
### How It Works

    - when an object of derived class is created, a copy of contents of superclass
    is made within it. 
        - (That's why you can access members of superclass!) 
        
    - Superclass reference variable CAN hold the subclass object, but using 
    that variable you can access  ONLY the members of the usperclass. 
    
        - in order to access members of BOTH classes, you need to create 
        a ref. variable to the subclass. 
        
    NOTE:
        - subclass inherits ALL MEMBERS from its superclass (unless they are private)
            - fields
            - methods
            - nested Classes
        - Constructors are NOT members of the class, so they aren't inherited,
        but they can be invoked.
        
### super keyword

    - similar to 'this' keyword. 
        - 'this' refers to the current member of the class
        
    - use cases
        - differentiate the members of superclass from members of subclass 
        if they have the same names. 
            
                ex
                        variable    vs.     super.variable
                        method()    vs.     super.method()
        
    - it can be used to INVOKE THE SUPERCLASS CONSTRUCTOR FROM THE SUBCLASS
        (this is the only way to invoke the superclass constructor from
        a subclass) 
        
### instanceof Keyword

    - this returns true if 'this' object is of the specified instance type. 
    

