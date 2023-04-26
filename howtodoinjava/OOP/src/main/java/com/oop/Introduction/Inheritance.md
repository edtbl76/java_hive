# INHERITANCE
- inheritance introduces a second degree of complexity beyond the Abstraction + Encapsulation 
relationship. 
    - In some cases there are objects with subtle differences. In these cases, it is 
    wasteful to create an entire object for both cases. 
    - it is more intuitive to create a single parent class that includes the common
    behavior between the two similar objects, allowing each object to vary independently. 
    
    - The relationships between the classes are referred to as:
        - BASE and DERIVED
            - because the derived class DERIVES its structure from the BASE. 
            - purely from a "name it based on what it does" perspective, this is
            my favorite terminology. I've warmed up to it.
        - SUPER and SUB
            - obvious reasons... my least favorite. 
        - PARENT and CHILD
            - overused concept, and it only moderately fits. CHILD is a temporary state. 
            eventually a child grows up into an Adult. 
            - yes I'm being pedantic, and I can if I fucking want to. 
            - The point I'm getting at is that a CHILD actually represents a human being
            in a presumably immature state. The analogy isn't quite accurate. 
            
            
            
    
    Check out the BaseExample for a basic example of inheritance
    

- a Derived class inherits all NON-PRIVATE MEMBERS 
    - fields, methods and nested classes. 
    - CONSTRUCTORS are NOT members of the class, therefore they aren't inherited
        - BUT the constructor of a BASE class can be invoked from 
        the DERIVED class.