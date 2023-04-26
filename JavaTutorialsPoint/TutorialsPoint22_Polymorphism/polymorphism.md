# POLYMORPHISM

    - generically, this is the ability of an object to take on many forms. 
    
    - in OOP, the most common uses occur when a parent class reference is used
    to refer to a child class object. 
    
    - Any Java object that can pass MORE THAN ONE "IS-A" test, is 
    considered to be polymorphic. 
    
        (THIS IS A COOL WAY TO REMEMBER IT) 
        
    - In Java.... ALL JAVA OBJECTS ARE POLYMORPHIC, because any object
    can pass the IS-A test for their own type and for the class 'Object'
    
    
    - NOTES ON REFERENCES (REFERENCE VARIABLES) 
        - the only possible way to access an object is through a reference 
        variable
        
        - since Java is strongly typed, once declared, a reference
        variable CANNOT BE CHANGED
        
        - reference variables can be reassigned to other objects (as long as 
        the variable hasn't been declared final, which makes it immutable)
        
        - the TYPE of reference variable determines the method 
        that can invoke it on the object
        
        - a reference var. can refer to any obj of its declared type or any 
        subtype of its declared type. 
        
        - a ref var can be declared as a class or interface type