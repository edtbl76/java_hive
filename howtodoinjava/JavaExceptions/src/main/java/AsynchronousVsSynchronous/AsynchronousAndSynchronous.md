# Asynchronous and Synchronous Exceptions

## Synchronous Exception
These are exceptions that "HAPPEN AT A SPECIFIC PROGRAM STATEMENT" no matter how many times we 
execute it. 
- EX:
    - NullPointerException
    - ArrayIndexOutOfBoundExceptions
    
## Asynchronous Exceptions
Can raise "practically anywhere"
- EX:
    - CTRL-C to interrupt a program
        - i.e. we don't know which line the program will end at.
    - signaling such as "stop" or "suspend" from another thread of execution
