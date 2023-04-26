# Local Variable Type Inference

## Declaration + Initialization
- when using 'var' it must be initialized at the same time it is declared


    YES:
        var i = 10;
        
    NO;
        var i;
        
## Not a Keyword
- var is not a keyword

    
    var var = "var";
        - This is allowed.
        
## USAGE
- allowed usage:
    - local variables w/ initializers
    - indexes in "enhanced for-loop"
    - internal variables in a traditional for-loop
- NOT ALLOWED
    - method parameters
    - constructor parameters
    - method return types
    - class fields
    - catch formals (or ANY OTHER KIND OF VARIABLE DECLARATION)
    
## POTPOURRI
- 'var' is NOT backward compatible
    - JDK10+ only
- 'var' has NO performance hit
    - type inference in Java occurs at COMPILE TIME, NOT AT RUN TIME 
    - the bytecode is the same as w/ explicit type declaration, so this is a
    "visual representation", "Code is for humans" situation.