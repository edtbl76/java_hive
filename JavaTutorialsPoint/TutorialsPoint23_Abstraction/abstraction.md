# JAVA ABSTRACTION

    - generically, this is the quality of dealing w/ ideas rather than events. 
    
    - in OOP, this is the process of hiding the implementation details drom the user, 
    so that ONLY the functionality will be provided to the user. 
        - the user knows what the objects does rather than how it does it. 
        
        CAR EXAMPLE:
            - most of us who drive cars have no friggin' clue what is going on
            after you turn the key and hit the gas. 
            
    IN JAVA, 
        - abstractoin is achieved using abstract classes and interfaces. 
        
### ABSTRACT CLASSES

    - classes declared w/ the abstract keyward are Abstract classes. 
    
    - abstract classes may/may not have abstact methods
        - (Abstract methods have no body) 
        
    - abstract classes can't be instantiated
        - they must be accessed through inheritance (by subclasses) and
        provide implementations to the methods inside it
        
    - if you inherit an abstract class, you have to provide implementations
    to ALL of the abstract methods in it. 
    
### ABSTRACT METHODS

    - if you want a class to contain a particular method but you want the actual 
    implementation of that method to be determined by child classes abstractly
    
     - 'abstract' keyword must be used before the method signature (and type) 
     
     - an abstract method still contains a signature, but can't contain a body
        - this is also signified by having semicolon at the end of the 
        declaration statement rather than curly braces. 
       