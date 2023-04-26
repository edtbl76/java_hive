# TEMPLATE DESIGN PATTERN

    BEHAVIORAL PATTERN
    
        - an abstract class exposes defined way(s)/template(s) to execute
        its methods
            - subclasses can override the method implementation as needed, 
            but the invocation is to be in the same way as defined by an
            abstract class. 
            
### IMPLEMENTATION

    - game abstract class defining operations w/ a template method set to
    final so it can't be overidden
    
    - concrete subclasses extend game and override its methods
    
    