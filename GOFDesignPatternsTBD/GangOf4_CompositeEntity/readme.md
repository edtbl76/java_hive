# COMPOSITE ENTITY PATTERN

    - used in EJB persistence mechanism
    
    - a composite entity is an EJB entity bean which represents a graph of objects
    
    - when the entity is updated, internally dependent objects beans get
    updated automatically as being managed by EJB entity bean. 
    
    COMPOSITE ENTITY
        - primary entity bean
        - may be coarse grained or can contain a coarse grained object to be
        used for the purpose of persistence
        
    COARSE-GRAINED OBJECT
        - contains depedent objects
        - has its own life cycle
        - manages life cycle of dependent objects
        
    DEPENDENT OBJECT
        - obj which depends on coarse grained obj for its
        persistence lifecycle
        
    STRATEGIES
        - stratgies represent HOW to implement a compoiste entity.