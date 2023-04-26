# Classes Vs. Objects

OBJECTS:
- container like data structure that represent the "ACTORS" in a system or app
- They have two main attributes:
    - STATE
    - BEHAVIOR
    
CLASSES:
- templates that describe the STATE and BEHAVIOR of its objects. 


## CLASS DECLARATION

    << modifiers >> class << class name >> {
        // fields and members of the class
    }
    
- class definitions may have ZERO OR MORE MODIFIERS
- a class is declared using the 'class' keyword
- the << class name >> is a user-defined name of the class 
    - it has to be a valid identifier
- each class has a body specified between its braces { }
    - contains fields and methods
    
    
## CLASS TYPES

- Abstract classes
    - they are INCOMPLETE
    - you can not create an instance of an abstract class. 
    - These can only be extended to COMPLETE their specification
- Non-Abstract classes
    - defines their FULL STATE and BEHAVIOR. 
    - considered COMPLETE, and they may be instantiated.
    
## CLASS COMPONENTS

- Fields 
    - (a.k.a class member variables)
- Methods
    - (a.k.a class member functions)
- Constructors
    - used to create objects of a class. 
    - every class has AT LEAST ONE CONSTRUCTOR FOR A CLASS
    - (java provides one if you don't write it)
- Static Initializers
- Instance Initializers
    - Initializers are used to initialize FIELDS of a class
    - zero or more initializers of static or instance types
    
### FIELDS  (NOUNS!)
- represent properties (a.k.a. STATE ATTRIBUTES) of objects of that class. 
    - declared inside the body of the class.
    
    
#### FIELD DECLARATION EXAMPLE
    
    public class Main {
    
        // FIELD DECLARATION
        << modifiers >> << data type >> << field name >> = << initial value >>;
    }
    
    EX:
        public class Human {
            String name;
            String gender;
        }
        
### METHODS (VERB)

- a collection of statements grouped together to perform an operation. 
- main use cases
    - modify the STATE of class fields. 
    - delegate tasks by calling methods in other objects
    
- may
    - accept 0 or more arguments
    - return void or a SINGLE value
    - be OVERLOADED (HORIZONTAL)
        - define more than one method w/ same name but different syntax
    - be OVERRIDED (VERTICAL)
        - define methods with SAME SYNTAX in parent and child classes. 
            
#### METHOD DECLARATION EXAMPLE

    public class Human {
        String name;
        String gender;
        
        public void eat() {
            System.out.println("Nom nom!");
        }
    }
    
### CONSTRUCTORS

- a named block of code used to initialize an object of a class immediately after it is created.
- can have an access-modifier
    - PUBLIC, PRIVATE, PROTECTED, or PACKAGE-PRIVATE (Default)
- Constructor Name must be the SAME as the simple name of the class
- must be followed by set of parentheses (that may include parameters)
- parentheses might be followed by exception list
    - starts with THROWS keyword. 
    - followed by comma-separated list of exceptions
    
- constructors don't have a return type ( NOT EVEN VOID) 
    - if it DOES have a return type, then it isn't a constructor, it is a method.
    
    
    NOTE: if the name of a CONSTRUCT is the same as the simple name of the class, then it can be
    either a METHOD or a CONSTRUCTOR
    
        - if it specifies a return type, then it is a CONSTRUCTOR
        - if it doesn't specify a return type, then it is a METHOD.

#### CONSTRUCTOR DECLARATION EX

    << Modifiers >> << Constructor Name >> ( << parmaters list >>) throws << Exceptions List >> {
        // Body of constructor goes here.
    }
    
### INSTANCE INITIALIZATION BLOCK (a.k.a. Instance Initializer)

- used to initialize objects of a class. 
- "just an UNNAMED block of code placed between a set of Braces"
    - outside any METHODS or CONSTRUCTORS
    
    
    EX:
    public class Main {
    
        {
            // instance initializer block
        }
    }
    
- There can be MULTIPLE instance initializers per class
- All initializers are executed AUTOMATICALLY in textual order for every object created. 
    - YIKES!
- Code for ALL INSTANCE INITIALIZERS ARE EXECUTED BEFORE ANY CONSTRUCTOR
- Initializers CAN NOT have a return statement
    - this is because they don't have a TYPE
- it can't throw CHECKED EXCEPTIONS unless ALL DECLARED CONSTRUCTORS list those checked exceptions in 
their throws clause.

### STATIC INITIALIZATION BLOCK (STATIC INITIALIZER)
- This is similar to an INSTANCE INITIALIZER, 
    - but it is used to INITIALIZE THE ENTIRE CLASS 
        - (whereas the INSTANCE initializer only initializes OBJECTS of a class)
    - this is executed only ONCE for a class when the class definition is loaded into the JVM
        - INSTANCE INITIALIZERS are executed once per object 
            - (which means they require that the object must be instantiated)
    - requires the use of the word STATIC keyword in the beginning of its declaration statement in 
    order to differentiate it w/ INSTANCE INITIALIZERS
    
    - we can have MULTIPLE static initializers per class. 
    - all STATIC INITIALIZERS are executed in textual order in which they occur. 
        - (they are also executed before any instance initializers)
        - (by induction, they are also executed before any constructors)
    - static initializers cannot throw checked exceptions
    - static initializers cannot have a return statement.
    
## CREATING OBJECTS

        EX
            << Class >> << variable >> = new << Call to Class Constructor >>;
            
            Human human = new Human();
            
            
- If we don't add a constructor to classes we create, Java will do it for us. 
- this is called the DEFAULT CONSTRUCTOR
    - no arguments
    - same name as the class
    
### NEW OPERATOR
- creates an  instance of a class by allocating memory in heap.

### NULL REFERENCE TYPE
- NULL reference has no name
    - this means there can be no variable of the null reference type. 
    - it has only one value defined by Java, which is the NULL LITERAL. 
- NULL REF type is ASSIGNMENT COMPATIBLE with any other ref type
    - this means that a 'null' value can be assigned to a variable of ANY other
    reference type.
    - practically, this means that the reference variable is pointing at NO OBJECT
- IMPORTANT NOTE:
    - null is a reference type and a reference type ONLY
    - this means that null can not be assigned to a primitive type.
    

    EX:
        Human john = null;      // john refers to NO OBJECT
        john = new Human();     // john refers to a valid Human object. 
