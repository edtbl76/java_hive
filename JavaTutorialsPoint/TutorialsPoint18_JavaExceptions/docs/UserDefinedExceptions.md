# User-Defined Exceptions

### Guidelines

    - all user-defined exceptions must be children of Throwable. 
    
    - If you want to write a CHECKED exception that is automatically enforced by
    the 'Handle' or 'Declare' rule, you need to extend the 'Exception' class
    
    - If you want to write a RUNTIME exception (i.e. unchecked) you need to 
    extend the Runtime Exception class
    
        EX:
            class MyException extends RuntimeException {
                // exception code.
            }