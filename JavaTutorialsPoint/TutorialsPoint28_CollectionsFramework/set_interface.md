# SET INTERFACE

    - extends Collection
    
    - similar to a list, but it cannot contain duplicate elements. 
    - models the mathematical set abstraction
    
    
    - adds 'stronger' contract on the behavior of 'equals' and 'hashCode' operations
        - allows Set instances to be compared meaningfully even if their
        implementation types differ
        
### METHOD

    - inherits methods defined by Collections Interface in addtion to its own
    
    1   void                add(Object obj)
                            - adds 'obj' to 'this' Set 
                            
    2   void                clear()
                            - removes all object from 'this' Set
                            
    3   boolean             contains(Object obj)
                            - returns true if 'obj' exists in 'this' Set
                            
    4   boolean             isEmpty()
                            - returns true if 'this' Set has no elements
                            
    5   Iterator            iterator()
    
                            - returns an iterator object for Set, which is used to 
                            retrieve objects from Set
                            
    6   void                remove(Object obj) 
                            - removes 'obj' from 'this' Set
                            
    7   int                 size()
                            - returns # of elements from 'this' Set
                            
            
        
        
      