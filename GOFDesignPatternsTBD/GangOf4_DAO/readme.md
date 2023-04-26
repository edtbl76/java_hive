# DAO (Data Access Object) Pattern

    - used to separate low level data accessing API or operations from high level
    business services. 
    
    DAO Interface
        - defines standard operations to be performed on a model objects
        
    DAO concrete class(es)
        - implements DAO interface
        - responsible for getting data from a data source (XML, DB, whateves) 
        
    ModelObject/ValueObject
        - simple POJO containing get/set methods to store data retrieved using
        DAO class
        
            POJO = Plain Old Java Object
            
    