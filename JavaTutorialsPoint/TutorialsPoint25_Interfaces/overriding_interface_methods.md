# Rules for Overriding Interface Methods

    - checked exceptions should not be declared in implementation methods
    other than the ones declared by the interface method or 
    subclasses of those declared by the interface method.
    
    - signature of the interface method and the same return type (or subtype) 
    should be maintained when overriding interface methods
    
    - an implementation class itself can be abstract
        - in this case, interface methods do NOT need to be implemented.