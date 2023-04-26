# Abstraction 
- that which captures only those details about a Java object that are relevant to 
the current perspective.

- in OOP, abstraction involves the facility to define objects that represent abstract
ACTORS that can perform work, report on and change their state as well as communicate w/ 
other objects in the system.

## ABSTRACTION TYPES

### DATA ABSTRACTION
- a method for creating complex data types
    - exposes only meaningful operations to interact w/ those data type
    - implementation details of creating, executing those data types are hidden from 
    external users
- Benefit
    - by hiding impl from external users, we can make performance improvements, 
    refactorings etc. w/o having any impact on the client code.

### CONTROL ABSTRACTION
- the process of identifying business logic, and exposing it as a single unit of work
    - i.e. repetitive statements, or complex sequence of statements, algorithms etc. 
- this hides the impl from the user so that they can USE our exposed functions w/o
having to understand how the result is created. 

## ABSTRACTION IN JAVA
- INTERFACES
    - allow abstracting of implementation completely
- ABSTRACT CLASSES
    - allows partial abstraction of implementation.
    
- DATA ABSTRACTION
    - spans from creating simple data objects to complex collections (i.e. HashMap/HashSet)
    
- CONTROL ABSTRACTION
    - defining simple function calls
    - using complete open source frameworks
    - main impetus for STRUCTURED PROGRAMMING
    
## ENCAPSULATION v. ABSTRACTION
- ENCAPSULATION is the realization of  your desired ABSTRACTION
    - abstraction is about hiding the implementation details
    
    - encapsulation is about wrapping the implementation (code) and the data it manipulates 
    (variables) in the same class. 
    
    - a JAVA class where all instance vars are private and only the methods in the 
    class can manipulate those vars is an example of an encapsulated class. 