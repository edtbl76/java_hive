# Traditional Exception Handling Process
New exceptions are created by extending 'Exception' class. 
- any time we need to throw an exception defined by this new exception, we create an instance of it
stuff it w/ info and throw it. 

- problem
    - there may be many use cases that aren't uniquely handled by the exception we created.
    
- solution
    - inner classes 
    
## Benefits of Inner Classes
- you can use instanceof comparison in different situations where you handle different
exception scenarios.
- you don't need to send single exceptions of a large set of exception conditions.
- easier to write unit te4st cases for negative cases where you know the exact exception class
you should expect. 
- logging is more meaningful and informative