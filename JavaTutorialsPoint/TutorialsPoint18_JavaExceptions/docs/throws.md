# Throws/Throw keywords. 

    - if a method doesn't handle a checked exception, the method must declare it
    using the 'throws' keyword. 
        - 'throws' statements should appear at the end of a method's signature
        (remember! method signature includes the name and list of parameters) 
        
    - exceptions can be raised directly, using the 'throw' keyword. 
        - this can be a newly instantiated exception, or one that was just caught
        (the latter case is called exception wrapping) 
        
    DIFFERENCE
        - throws postpones the handling of a checked exception
            - DO IT LATER!
        - throw is used to explicitlly invoke an exception.
            - DO IT NOW!
            
            
    - methods can declare that they throw more than one exception by separating
    the list of exceptions by commas. 