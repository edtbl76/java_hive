# Java Collections and Generics

## GENERICS
provide type safety
- they are used to detect incompatible types in method arguments. 
- prevents ClassCastExceptions at runtime. 
- Collections use Generics to set the type that a collection class will be used to
store. 

    
    EX:
    
        HashMap<Integer, String> map = new HashMap<>();
        
        This hash map only accepts keys as Integers and Values as Strings.