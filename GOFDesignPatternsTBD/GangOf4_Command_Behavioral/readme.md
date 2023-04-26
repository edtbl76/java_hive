# Command Pattern (Broker/Provider?)

    BEHAVIORAL PATTERN
    
    - a request is wrapped under an object as a command and passed to an 'invoker' 
    object. 
    - 'Invoker' object looks for the appropriate object which can handle the 
    requested command and passes the command to the corresponding obj which
    executes the command. 
    
### Implementation

    - Order interface acts as a command
    - Stock class acts as request
   
    - ButStock. SellStock concrete classes implement Order. 
    - Broker class is the Invoker.
    
