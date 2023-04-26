# Visitor Pattern

    BEHAVIORAL
        
        - a visitor class changes the executing algorithm of an element class
            - execution of algorithm of element can vary as and when the
            visitor varies. 
            
            - the element object has to ACCEPT the visitor object so that the
            visitor object handles the operation against the element
            
### IMPLEMENTATION

    - ComputerPart interface that defines an accept operation
    - there are concrete classes that implement it.
    - ComputerPartVisitor which defines visitor class operations. 