# INHERITANCE
- IS-A Relationship (Inheritance)
    - capability where DERIVED objects inherit or acquire ALL of the properties and 
    behaviors from the BASE object. 
- in OOP, this promotes CODE REUSE

## INHERITANCE IN JAVA
- 'extends' keyword is used for inheritance between classes. 
- DERIVED classes inherit ALL NON-PRIVATE MEMBERS from the BASE class, by default.

## TYPES OF INHERITANCE

### SINGLE INHERITANCE
- One Base Class, One Derived Class.
- Pretty straight forward
    - derived class gets everything from the BASE class hat isn't private.
    
### MULTI-LEVEL INHERITANCE
- The DERIVED CLASS of a BASE class becomes the BASE CLASS for ANOTHER DERIVED CLASS


    EX: 
        CLASS B extends Class A
        CLASS C extends CLASS B
        
        CLASS B is a Derived Class of CLASS A
        CLASS B is a Base Class to Class C
        
- In this case, Class C inherits the traits of both Class B and Class A.
        
### HIERARCHICAL INHERITANCE
- this is when a BASE class has several derived classes. 


    EX: 
    
    
        CLASS B extends CLASS A
        CLASS C extends CLASS A
        CLASS D extends CLASS A
    

- each of the classes that extend Class A inherit its properties, but their own
local implementation varies separately

### MULTIPLE INHERITANCE
- In this case, a DERIVED class may inherit behavior from MORE THAN ONE PARENT CLASS
- Java only has loose support for this
    - in JDK 8
        - interfaces support multiple inheritance w/ default methods.
        
        
        EX:
            CLASS B extends CLASS A
            CLASS C extends CLASS A
            CLASS D extends CLASS B
            CLASS D extends CLASS C
            
            (You'll note this forms a diamond)
            
## RULES FOR ACCESSING INHERITED BASE CLASS MEMBERS

### CALLING CONSTRUCTORS OF PARENT CLASS

    - super() must be made from child class constructor
    - super() must be the FIRST STATEMENT inside the constructor
    
### PARENT CLASS FIELDS
- non-private member fields can be inherited in a child class. 
    - they are accessed using DOT OPERATOR
- NOTE:
    - FIELDS CANNOT BE OVERRIDDEN. 
    - this means that having the same name field in a derived class will HIDE
    the field from the parent class
    
### PARENT CLASS METHODS