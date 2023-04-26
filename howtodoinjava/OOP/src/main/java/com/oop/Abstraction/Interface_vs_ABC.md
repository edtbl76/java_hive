# Interfaces vs. Abstract Classes: In Java. 
- These are the 2 main building blocks of most Java APIS

## Abstract Classes
- a class declared as abstract using the keyword..'abstract'
    - it may/may not contain any abstract methods
- identified by JVM as an INCOMPLETE CLASS

- declaring a class ABSTRACT has ONE guarantee
    - you cannot create an instance of this class. 
    
### ABSTRACT METHODS
- a method that isn't implemented in place. 
    - adds "incompleteness" to class
    - so compiler will declare ENTIRE CLASS as abstract
    
- only way to USE abstract classes is to extend them to another class
    - non-abstract derived classes can be instantiated. 
    
## Interfaces in Java
-  an interface defines a CONTRACT, which implementing classes must abide. 
    - these contracts are usually unimplemented methods. 
- All methods of an interface are inherently:
    - PUBLIC and ABSTRACT
    - (this is extended in later versions of Java)
    
# WHEN TO USE WHAT

## ABSTRACT CLASSES --> PARTIAL BEHAVIORS
- abstract classes allow you to define SOME behaviors, making them excellent 
candidates for use inside frameworks. 
- they are great for providing the foundation from which to build a more specific 
implementation (i.e. they might contain business logic and initialization steps
to set up a general pattern that can be extended via more specific use cases.)

## CONTRACT ONLY INTERFACES
- Interfaces provide the contracts or "Rules"
- it is up to the implementing classes to implement EACH AND EVERY ONE. 
