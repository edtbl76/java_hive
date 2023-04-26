# Decorator Design Pattern

    - STRUCTURAL PATTERN
    
        - allows user to add new functionality to an existing object w/o
        altering it sstructure. 
        
        - creates decorator class and provides additional functionality
        keeping class methods signature intact.
        
### Implementation

    - Shape interfce w/ concreate classes implementing the Shape interface
    - abstract decorator class ShapeDecorator that implements Shape interface
    - DecoratorPatternDemo will use a concreate class that implements the 
    abstract decorator class to decorate Shape objects
    