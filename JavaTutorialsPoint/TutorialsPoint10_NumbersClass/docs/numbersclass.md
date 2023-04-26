# Numbers Class

    numerical primitives:
        byte
        int
        long
        double
        flat
        short
        
    
    in some situations the primitive data types aren't sufficient, so we 
    have to use 'wrapper classes'. 
    
    java.lang.Number is an abstract java class. As we recall.. abstract classes can only
    be accessed through their subclasses. 
    
    The wrapper classes of Number are:
    
        Integer
        Long
        Byte
        Double
        Float
        Short
        
    The 'object' of each wrapper class contains(wraps) its respective data type. 
    
    
    BOXING:
        - this is the process of converting primitive data types into an object. 
        - (Don't worry, the compiler does this for you!) 
        - the compiler also UNBOXES the object back into a primitive data type.
        
### Methods of java.lang.Number
    
    1   xxxValue()
    
            Converts the value of 'this' Number object to xxx data type and returns
            the value
            
    2   compareTo()
    
            Compares 'this' Number object to the argument passed to the method
            
    3   equals()
    
            Detemines if 'this' Number object is equal to the arg passed to the method
            
    4   valueOf()
    
            returns an Integer object holding the value of the specified primitive
            
    5   toString()
    
            returns a String object representing the value of a specified 'int' or
            'Integer'
            
    6   parseInt()
    
            used to get primitive data type of a certain 'String'
            
    7   abs()
    
            Returns absolute value of the arg passed to the method
            
    8   ceil()
    
            Returns the smallest 'int' that is >= to the arg passed to the method.
            (value is returned as a DOUBLE) 
            
    9   floor()
    
            Returns the largest 'int' that is <= to the arg passed to the method.
            (value is returned as a DOUBLE) 
            
    10   rint()
    
            Returns the 'int' closest in value to the arg passed to the method
            (value is returned as a DOUBLE)
            
    11  round()
    
            Returns the closest 'long' o 'int' (as indicated by method's return
            type to the arg) 
        
    12  min()
    
            Returns the smaller of the 2 args passed to the method
            
            
    13  max()
    
            Returns the larger of the 2 args passed to the method
            
    14  exp()
    
            Returns base of natural logarithms, e, to the power of the arg.
            
    15  log()
    
            Returns the natural logarithm of the arg
            
    16  pow()
    
            Returns the value of the first arg raised to the power of the 
            2nd arg
            
    17  sqrt()
    
            Returns square root of the arg
            
    18  sin()
    
            Returns the sine of the specified 'double' value
            
    19  cos()
    
            Returns the cosine of the specified 'double' value
            
    20  tan()
            
            Returns the tangent of the specified 'double' value
            
    21  asin()
    
            Returns the arcsine of the specified 'double' value
            
    22  acos()
    
            Returns the arcosine of the specified 'double' valuer
            
    23  atan()
    
            Returns the arctangent of the specified 'double' value
            
    24  atan2()
    
            Converts rectangular coordinates (x, y) to polar coordinate (r, theta)
            and then returns theta.
            
    25  toDegrees()
    
            Converts arg to degrees
            
    26  toRadians()
    
            Converts arg to radians
            
    27  random()
    
            Returns a random number
            
    