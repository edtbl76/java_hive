# Java Methods

    - a collection of statements grouped together to perform an operation
    - VERBS!!!!!!!!
    
        - may return a value 
            (if so, the method is assigned the type of its return value)
        - 'void' methods return no values
        
        - may include parameters 
            (arguments that provide information data to assist w/ the operation 
            being performed by the method) 
            
### Creating a Method

    public static int methodName(int a, int b) {
      // body
    }
    
        modifier(s):            public static
        return type:            int
        name:                   methodName
        formal parameters       a, b
        list of parameters      int a, int b
        
        
    modifier returnType nameOfMethod (Parameter List) {
      // method body
    }
    
        modifier: 
                - defines access type of method (optional) 
                
        returnType:
                - type of value returned by method (void if none)
                
        NOTE: Method Signature is the aggregate of its name and parameter List
                
        nameOfMethod:
                - method name. 
        
        parameterList
                - list of parameter arguments to be passed to the method
                    - type, order and # of parameters
                - params are optional
                
        methodBody
                - what the method does!
                
### Method Calliing

    - using a method is referred to as "calling"
        - methods either return a value or they return nothing
        
    - PROCESS
        - program invokes a method,
        - program control gets transferred to the called method
        - called method returns control to the caller in TWO conditions:
            - when return statement is executed OR
            - it reaches the method ending closing brace
            
            
### 'void' Keyword

    - 'void' is the keyword that allows the creations of methods that don't 
    return values. 
   
### parameters 

    - args should be passed in the same order as their respective parameters
    in the method specification. 
    - parameters may be passed by value or by reference.
        - parameters by value means calling a method w/ a parameter.
            - through this the argument VALUE is passed to the parameter
                            