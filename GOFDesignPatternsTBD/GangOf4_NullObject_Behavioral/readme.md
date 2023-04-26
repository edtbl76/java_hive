# NULL OBJECT PATTERN

    BEHAVIORAL PATTERN
    
        - a null object replaces a 'check' of NULL object instance. 
        - instead of putting if check for a null, Null Object reflects a 
        "do nothing" relationship.
         
            - usually used to provide a default behavior case when data
            isn't available. (Safety net)
            
### IMPLEMENTATION

    - AbstractCustomer class defining operations
    - the name of customer and concreate classes extend the abstract class. 
    - factory class is created to return a RealCustomer or NullCustomer based
    on the name of the customer passed to it.