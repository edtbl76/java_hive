# Local Variables

    These are variables that are defined inside
    - methods
    - constructors
    - blocks
    
    This variable is declared + initialized when program enters
    the method, constructor or block and is destroyed when method, constructor
    or block is exited
    - ergo... local variables are only visible within the scope in which 
    they are declared (i.e. the method, constructor or block) 
    
    ACCESS MODIFIERS: No support 
    
    local variables are implemented at 'stack level' internally
    
    THERE IS NO DEFAULT VALUE FOR LOCAL VARIABLES
    - This means that they must be declared AND initialized before they can be used. 
    
    
# Instance Variables

    Variables within a class but OUTSIDE of a method, constructor or block. 
    
    When a space is allocated on the heap, a slot for each INSTANCE VARIABLE is created.
    
    Instance Variables are created when an object is created with the keyword 'new'
    (i.e. the class is instantiated) and are destroyed when the object is also destroyed. 
    
    The PURPOSE of an instance variable is to be available to many 
    methods/constructors/blocks or a class OR as an essential component of an object's 
    state, that must be present throughout the class. 
    
    These variables can be acessed from inside any
    - method
    - constructor
    - block
    OF the class it is defined in.
    
    ACCESS-MODIFIERS: Full support! 
    
         NOTE: it is normally recommended that variables are given private access where external
    entities require the use of getters/setters (well-defined interface!) to access
    modify the data. 
        
        (Protected access for subclasses or package-private modifiers can be used for
    relevany objects/packages) 
    
    
    INSTANCE VARIABLES HAVE DEFAULT VALUES:
    - integral/numerics have 0 (0.0f, 0.0d, etc.) 
    - strings/chars are null
    - values can be assigned during declaration of the object OR within the constructor
    
    Instance Variables can be accessed directly by calling the variable name inside the
    class. 
    
    - NOTE: in static methods (when instance variables are given accessibility), they
    should ONLY be callued using the FQN (Fully Qualified Name) 
    
        EX: ObjectReference.VariableName
        
        
# Class/Static Variables

    These are variables declared within a class, but 
    OUTSIDE of a method 
        - AND they are defined w/ the static keyword. 
        
    There is only ONE copy of each class variable per class, regardless of how many 
    objects are created from it. 
    
    -Use Case = CONSTANTS, or values that never change from their initial value. 
        EX:
            public/private final static int pi = 3.14
        NOTE:
            rarely used other than being declared as constants
            (i.e. they are almost always public/private final static) 
    
    - static final vars should be declared as constants (all upper case), in the rare
    instance that they are not constants, the naming syntax for instance/local variables 
    applies. 
          
    Stored in static memory
    - This means that they are created when the program starts and destroyed when the 
    program stops .
    '
        NOTE: be careful here. it is very easy to overload a program w/ too many 
        static values and create an app that suffers from bloat. 
        
    
    ACCESS/VISIBILITY:
        - similar to instance variables, but are usually 'public' as to be fully 
        available to any users of the class. 
        
        (The storage/perf penalty may 
        very well outweigh the value of a private constant) 
        
    DEFAULT VALUES
        - same as instance variables
            -  number/integral values are 0 (or the like, 0.0f, 0.0d)
            -  string/char data types are null
        - values can be assigned during declaration or constructor (also like
        instance variables) 
            - BUT.... values can also be assigned in special static initializer blocks
            
    
    accessed by calling w/ class name 
        EX: ClassName.VariableName.
        
    
        

        
   