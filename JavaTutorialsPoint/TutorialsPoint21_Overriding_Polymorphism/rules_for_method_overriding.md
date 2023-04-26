# RULES FOR OVERRIDING

    - the argument list must be EXACTLY the same as the overridden method. 
    
        - (A method signature is its name + its list of parameters)
        
        - Overriding requires identical method signatures, when the name is
        the same, but the list of parameters are different, it is overloading) 
        
    - the return type should be:
        - the same
        - a subtype of the return type in the overridden parent method
    
    - the access level can't be more restrictive than the overriden
    method's access level. 
        - i.e. if superclass method is declared public, the overriding method
        MUST be public as well. 
        
    - Instance methods can only be overriden if they are inherited by the subclass
    
    - methods that are declared final, cannot be overridden.
    
    - a method declared static can't be overridden, but it CAN be redeclared.
    
    - if a method can't be inherited, in can't be overriden. 
        - (POLYMORPHISM REQUIRES INHERITANCE) 
        
    - a subclass in the same package as the instance's superclass can override
    ANY superclass method that hasn't been declared static or final.
    
    - a subclass in DIFFERENT packages can only override non-final methods that
    are declared public or protected. 
    
    - an overriding method can throw any uncheck exceptions (even if the overridden
    method throws those (or any) exceptions) BUT
        - overriding method should NOT throw checked exceptions that are new or broader than
        ones declared by the overriden method.
        - overriding method can throw narrower or fewer exceptions than the overridden
        method
    - constructors can't be overriden 
        (This makes sense because constructors are not members of the class) 