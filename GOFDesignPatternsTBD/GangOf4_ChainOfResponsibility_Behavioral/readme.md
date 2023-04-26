# Chain of Responsibility Pattern

    BEHAVIORAL PATTERN
    
        - creates a chain of 'receiver' obj for a request. 
        - purpose is to decouple sender and receiver of a request based on type
        of request. 
            - NORMALLY, each receiver contain a reference to another receiver, if
            one obj can't handle a request, it passes the same to the next rcvr 
            and so on.
            
            
### IMPLEMENTATION

    - create abstract class AbstractLogger 
    - create 3 types of loggers extending AbstractLogger
    - each logger checks log level message and prints accordingly, otherwise it
    hands off to next logger