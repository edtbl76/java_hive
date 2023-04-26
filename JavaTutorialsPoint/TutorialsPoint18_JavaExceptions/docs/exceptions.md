# Exceptions

    - defined as a problem that arises during the EXECUTION OF  PROGRAM
    
    - disrupts the normal flow of the program leading to abnormal termination, such
    that the exception needs to be happened. 
    
        - COMMON CULPRITS
            - invalid data entered by a user
            - file not found during file operation 
            - network connection has been lost in middle of comms
            - JVM is out of memory
            
    COMMON EXCEPTIONS/ERRORS
    
    - JVM  Exceptions
        - exceptions/errors exclusively thrown by the JVM
            - NullPointerException
            - ArrayIndexOutOfBoundsException
            - ClassCastException
    
    - Programmatic Exceptions
        - exceptions/errors thrown explicitly by app or API programmers
            - IllegalArgumentException
            - IllegalStateException
            
### Checked Exceptions (AKA Compile-Time Exceptions) 

    - exception that is checked (imagine that) i.e. it has been notified by
    compiler at compile-time. 
    - these CAN NOT BE IGNORED, they MUST BE HANDLED BY DEV
    
### Unchecked Exceptions (AKA Runtime Exceptions)

    - exception that occurs specifically at the time of execution. 
    - types:
        - programming bugs (logic errors, improper use of API) 
    - these are IGNORED at Compile Time.
    

### Errors (non-Exceptions)

    - "problems that ariase beyond the control of the user or programmer"
    - usually ignored in code, because there is rarely anything you can do about
    the error. 
    - usually ignored at compile time as well (undectectable by compiler?) 
    
    
### Catching Exceptions (Protected Code)

    - try/catch keywords are used to "catch" exceptions
        - try block wraps statements that may generate exceptions 
            - (When wrapped by try block, code is PROTECTED CODE)
            
        - catch block provides code to be executed if the exception has been
        encountered. 
            - exception being caught is specified here. 
            - multiple catch blocks for different exceptions
            
    - if catch block is config'd to the exception being thrown, then control of
    the program is transferred to the catch block. otherwise the exception isn't handled 
    and the program/app terminates fatally. 
    
    
        EX: 
        
            try {
                // Protected Code
            } catch (ExceptionType1 e1) {
                // catch one
            } catch (ExceptionType2 e2) {
                // catch two
            } catch (ExceptionTypeN eN) {
                // catch three
            } 
            
        NOTE: as of Java 7, if the exceptions aren't parent/child of each other, 
        you can do the following. (This helps prevent code duplication) 
        
             try {
                // Protected Code
             } catch (ExceptionType1|UnrelatedException e) {
                // catch code
             }
          
### Finally

    - the finally block lives at the end of a try/catch block and ALWAYS
    executes regardless of what happens inside the protected block, or if an
    exception occurs. 
    
    - USUALLY used for clean up statements so that resources are released, or 
    refs are closed after protected code or so that thrown exception doesn't 
    result in further breakage. 
          
### NOTES ON TRY-CATCH-FINALLY

    - catch can't exist w/ a try statement
    
    - finally isn't required (but is recommended if your code will leave behind 
    messes in the event of exceptions) 
    
    - the try block cannot be present w/o either catch or finally
    (or in Java 7+, it requires an auto resouce mgmt block) 
    
    - code can't be present BETWEEN the try, catch and/or finally blocks.
    
    