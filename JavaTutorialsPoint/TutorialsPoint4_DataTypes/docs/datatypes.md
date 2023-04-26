# Java DataTypes

    (Remember, variables are just reserved memory locations
    to store information)
    
    Creating a variable is the same as storing data in mem.
    
### Primitive Data Types

    Java supports 8 primitive data types
    
    - 1.) byte
        - 8 bit signed two's complement integer
        - values can range from 
            - -128 (-2^7)
            - 127 ((2^7) - 1)
        - default = 0
        - use cases
            - uses to save space in large arrays in place of
            integers (byte is 4 x smaller than int) 
            
    - 2.) short
        - 16 bit signed two's complement integer
        - values can range from:
            - -32,768 (-2^15)
            - 32,767 ((2^15) - 1)
        - default = 0
        - use cases
            - also used for space saving (2 x smaller than an
            integer), but w/ less restrictions than a byte.
            
    - 3.) int
        - 32 bit signed two's complement integer
        - values can range from
            - - 65,536 (-2^31)
            - 65,535 ((2^31) - 1)
        - default = 0
        - use cases
            - default data type for integral values when not
            concerned about memory.
            
    - 4.) long
        - 64 bit signed two's complement integer
        - values can range from
            - - 131,072 (-2^63)
            - 131,071 ((2^63) - 1)
        - default = 0L
        - use cases
            - used when we need numbers/values greater than
            32 bits. 
     
    - 5.) float
        - single precision 32-bit IEEE 754 floating point.
        - default = 0.0f
        - NEVER USE FLOAT FOR PRECISE VALUES
            - currency
            - life or death calculations
            - nuclear power plants
        - use cases:
            - save memory space in large arrays of floating point
            numbers
   
    - 6.) double
        - double precision 64-bit IEEE 754 floating point
        - default = 0.0d
        - NEVER USE DOUBLE FOR PRECISE VALUES
            - currency
            - life or death calculations
            - nuclear power plants
            - measuring penis size
        - use cases:  
            - default data type for decimal values unless
            storage/memory space is a concern.
            
    - 7.) boolean
        - represents one bit of information
        - possibles values
            - true
            - false (default) 
        - use cases:
            - basic binary logic
            
    - 8.) char
        - 16-bit Unicode character
        - values can be:
            - minimum: 0        (\u0000)
            - maximum: 65,535   (\uffff)
        - use cases:
            - store characters/symbols 
            

### Reference/Objects Data Types

    - created using defined constructors of classes
        - declared to be of a SPECIFIC TYPE THAT CANNOT BE CHANGED
        (EX: Puppy, Employee)
    - used to ACCESS objects
    
    - Class objects and various types of Array variables come 
    under the 'reference' data type
    
    - Default = null
    
    - can be used to refer to ANY object of the DECLARED type or
    any compatible type
    
    EX: 
        Animal animal = new Animal("giraffe") 
        
        (The reference is the second word in that statement) 
        
        
    
        
