# Access Control and Inheritance

    RULES FOR INHERITED METHODS
    
        - methods declared public in superclass must also be public in all  
        superclasses
        
        - methods declared protected in superclass must either be public or protected
        
        - private methods are not inherted
        
        (The pattern here is that subclasses can NEVER be more restrictive than their
        parent classes) 