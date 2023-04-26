# Auto Resource Management 
## (AKA) try-with-resources

    - exception handling mechanism introduced in Java 7 which 
    automatically closes resources used inside a try/catch block. 
    
    - to use this feature, the resources must be declared in parentheses between
    the try keyword and the brackets for the protected code. 
    
### Constraints

    - to use a class w/ try-w/-resources statement, it must implement 
    'AutoCloseable' interface and the close() method of it gets invoked
    automatically at runtime. 
    
    - more than one class can be declared in try-with-resources
        - NOTE: they are separated by semicolons, but there is no semicolon
        after the LAST class in the try-with-resources block
        
        - NOTE: when multiple classes are declared, they are closed in REVERSE
        order (this means that the first class declared is the last to be closed)
        
    -   resources declared in the try-with-resources block are instantiated
    immediately before the try-block
    
    - try-with-resources classes are implicitly declared as final (making them 
    immutable) 
    
    
    