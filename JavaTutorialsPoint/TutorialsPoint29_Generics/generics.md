# Generics

    - Java Generic methods and classes enable devs to specify (w/ a single method
    declaration) a set of related methods or (w/ a single class declaration) a
    set of related types, respectively
    
    - provide compile-time type safety that allows devs to catch invalid types at
    compile time. 
    
    
    EX:
        wrte a generic method for sorting an array of objects, then invoke the
        generic method w/ Integer arrays, Double arrays, String arrays and so on
        to sort the array elements
        
        
### GENERIC METHODS

    - you can write a single generic method declaration that can be called w/ 
    args of different types. 
        - based on the types of args passed to the generic method, the compiler
        handles each method call appropriately
        
        
    RULES
        - all generic method declarations have a type parameter section
        delimited by angle brackets (< and >) that precedes the 
        method's return type (< E > )
        
        - each type parameter section contains ONE OR MORE type parameters separated
        by commas. 
            - a "type parameter"/"type variable" is an identifier that specifies a
            generic type anme. 
            
        - the type parameters can be used to declare the return type and act as
        placeholders for the types of the args passed to the generic method (which are known
        as "actual type arguments") 
    
        - generic method's body is declared like that of any other method. 
        
            - type parameters can only represent REFERENCE TYPES .
            - type parameters can NOT represent primitive types (int, double, char, etc.) 
            
### BOUNDED TYPE PARAMETERS

    - bounded type parameters are used to restrict the kinds of type3s that are
    allowed to be passed to a type parameter. 
    
    - to declare 'bounded type parameters', list the type parameter's name, followed by
    the extends keyword, followed by its upper bound. 
    
### GENERIC CLASSES

    - a generic class declaration looks like a non-generic class declaration, but the
    class name is followed by a "TYPE PARAMETER" section.
    
    - type parameter section of a generic class can have 1 or more type parameters 
    separated by commns. 
    
    - these classes are known as 'parameterized classes' or 'parameterized types'
    because they accept one or more parameters. 