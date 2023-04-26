# TRY BLOCK
- contains app code that is "expected to work under normal conditions"
- This is the ONLY mandatory block.


    try {
        // code
    }
    
# CATCH BLOCK
- follows try block and MUST handle checked exceptions thrown by try block as well 
as any possible unchecked exceptions


    SINGLE:
    
    try {
        // code
    } catch (Exception ex) {
        // handle exception
    }
    
apps can crap out in multiiple ways, so multiple blocks are allowed

    MULTI
    
        try {
            // code
        } catch (NullPointerException ex) {
            // handle NPE
        } catch (NumberFormatException ex) {
            // handle different exception
        }
    
# FINALLY BLOCK
- this is an opportunity to run code REGARDLESS of what happens in the try/catch 
- executes rain or shine!
(usually for cleaning up after ourselves) 


    try {
        // do stuff
    } catch(Exception ex) {
        // handle
    } finally {
        // clean up
    }
    