# Object-Oriented Concepts in Java

(The big four)
- polymorphism
- inheritance
- encapsulation
- abstraction

- Classes
- Objects
- Instance
- Method
- Message Passing

# Inheritance

    classes can be derived from other classes. 
    
    This allows you to reuse the exisitng fields + 
    methods of the existing class w/o having to 
    rewrite the code in a new class. 
    
    superclass = 'parent class' or 'existing class'

    subclass = 'child class' or 'derived class'


# Interfaces

    defined as a contract between objects on how to communicate
    with ach other. 
    
    an interface defines methods that a subclass should use, 
    BUT the implementation of the methods is totally up 
    to the subclass. 
    
# Objects

    have states and behaviors (nouns and verbs)
    
    An object is an INSTANCE of a class. 
    
    3 steps when creating an object (instance) from a 
    class (template/blueprint). 
    
    - Declaration:
        a variable declaration w/ a variable name w/ an 
        object type
        
    - Instantiation:
        The 'new' keyword is used to create the object
        
    - Initialization:
        The 'new' keyword is followed by a call to 
        a constructor.
        
        (This call is what to constructor. This call is 
        what initializes the new object)
    

# Class

    a class is a template/blueprint that describes the
    state and behavior that an object of its type
    can/will support
    
### Constructors

    Every class must have a constructor. 
    
    Even if we don't WRITE a constructor, the compiler
    will build a default constructor for that class. 
    
    Every time a new object is created, at least one
    constructor is invoked. 
    
    Constructors MUST have the same name as the class. 
    
    A class can have more than one constructor
    (this is a type of overloading)