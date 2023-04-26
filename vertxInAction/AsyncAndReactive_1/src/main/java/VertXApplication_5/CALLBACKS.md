# CALLBACKS
Vert.x uses callbacks as the primary method for Vert.x to notify of 
asynchronous events and pass them to the handlers. 
- combined w/ Lambda notation, they make for a very concise way for defining event
handlers.

## CALLBACK DEFINED
- A call back is a function that must be executed after another function has 
finished executing. 
    - this is useful in asynchronous development, because the call back is
    a function that notifies the producer that the consumer has done something.
    
- in terms of "FIRST ORDER FUNCTIONS", a callback is a function that is passed
as an argument. 
    - what to do when the function it was provided to is done. (usually) 