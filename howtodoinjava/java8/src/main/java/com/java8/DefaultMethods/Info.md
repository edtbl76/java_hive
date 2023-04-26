# Default Methods
- introduced in Java8, these are simply "default"
    - if you don't override them, they are the methods invoked by caller classes. 
    - they are defined in INTERFACES.
    
## BENEFITS
- static default methods
    - you can define static default methods in an interface
        - available to ALL instances of class which implements the interface
        - makes it easier to organize helper methods in your libs
        - keep static methods specific to an interface in the same interface instead of
        in a separate class. 
        - enables you to define methods OUT of your class yet share w/ all child classes
- provide HIGHLY desired capability of adding a capability to a number of classes w/o 
even touching their code. 
    - just add a default method in the interface which they all implement.
    
## WHY DEFAULT METHODS WERE ADDED TO JAVA 8
- to enable LAMBDA EXPRESSIONS in Java. 
- Lambda Expressions are (basically) a type of FunctionalInterface. 
- to support lambda expressions seamlessly, all of the core classes had to be modified. 

The only way to do this effectively was to implement Default Methods to enable support for
functional interfaces in core classes. 

    EX:
    
        default void foreach(Consumer<? super T> action) {
            Objects.requireNonNull(action);
            for (T t : this) {
                action.accept(t);
            }
        }
        
## RESOLVING CONFLICTS WHEN CALLING DEFAULT METHODS
- an interface can extend another interface
- a class can implement N number of interfaces. 

- if any default method is declared ini 2 such interfaces which are implemented by a single
class, then there is confusion about which one should be called. 

RULES
- most preferred are overriden methods in the classes. 
    - They will be matched and called if found before matching anythiing
- the method w/ the same signature in the "most specific default-providing interface" is selected
    - EX: if Moveable and Walkable have the same default method, but Walkable extends Moveable, 
    then Walkable wins as the most specific
- if the two interfaces are independent, the compiler is going to blow up and you'll have to 
choose it explicitly in the code.