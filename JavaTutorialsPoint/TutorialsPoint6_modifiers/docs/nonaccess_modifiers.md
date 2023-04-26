# Non-Access Modifiers


    STATIC      - used for creating class methods and class variables
    
        Static/Class Variables
            - vars exist independently of any instances creted forthe class. 
            - one copy of the var exists, regardless of the # of instances of the class
            - must be declared outside of methods, constructors or blocks 
                (i.e. Local Variables can't be declared static) 
                
        Static/Class Methods. 
            - methods exist independently of any instances created for the class. 
            - they don't use instance variables of ANY object of the class they are
                defined in. 
            - take all data from parameters passed to the method and perform 
            operations against those variables w/ no reference to variables. 
            
        
        Class/Static Variables & Methods can be accessed by ClassName.<name> where <name>
        is thee name of the Class/Static Variable or Method. 
    
    
    FINAL       - used for finalizating the implementation of classes, methods and variables
                (this means the value cannot be changed further) 
    
        Final Variables
            - initialized only once. 
            - reference variables declared 'final' can never be reassigned to refer to 
            a different object. 
                - NOTE: 
                    While the reference can NOT be changed the data within the object
                    (i.e. the STATE) can be changed. 
                    
            - final + static together make a variable a CONSTANT
            
        Final Methods
            - can't be OVERRIDDEN by subclasses. 
            - (the purpose here would be preventing the content of the method from being
        changed by an outsider) 
        
        
        Final Class
            - prevents subclassing of the class (i.e. blocks/prevents inheritance) 
            
        
    ABSTRACT    - used for creating abstract classes and abstract methods
    
        Abstract Class
            - can never be instantiated. 
            - the entire purpose of an abstract class... is for it to be extended(or 
            subclassed) 
            
            NOTE: an abstract class can NEVER be final, because final classes prevent
            subclassing. (This is a paradox). 
            
            - abstract classes can contain abstract OR normal methods. 
            
        Abstract Method:
            - This is a type of method declared w/o any implementation. 
            (The body/implementation  of the method is provided by the subclass. 
            
            - can NEVER be final or strict (as this would paradoxically prevent
            implementation within a subclass) 
            
            - any class that contains abstract methods MUST be an abstract class, but
            abstract classes are not required to have abstract methods. 
            
            - because there is no implementation... ALL abstract methods end in 
            semicolons. 
    
    SYNCHRONIZED    - used for threads
    
        - used to indicate that a method can be accessed by only one thread at a time. 
        - (This is supported by ALL four of the access level modifiers) 
        
    TRANSIENT
    
        - used for INSTANCE VARIABLES to indicate to the JVM to skip this particular
        variable when serializing the object containing it. 
        (This means the data doesn't persist through serialization)
        - this is included in the statement that CREATES the variable (preceding the
        class or data type of the variable) 
        
    VOLATILE        - also used for threads. 
    
        - used to let the JVM know that a thread accessing a variable must ALWAYS
        merge its own private copy of the var w/the master copy in  memory. 
        
        - accessing volatile variables synchronizes ALL cached copies of the variables in 
        main memory
        
        - ONLY supported by instance variables
        - ONLY supports type object or private. 
        - supports null obj4ct references. 