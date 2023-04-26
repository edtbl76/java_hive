# HashSet Class

    - extends AbstractSet
    - implements Set
    
    - creates a collection that uses a HashTable for storage
    - information is stored by HASHING
    
    HASHING
        - 'informational content' of a key is used to determine a unique value
        - hash code (the value determined by the previous step)
        
### CONSTRUCTORS

    HashSet()               - constructor builds a default HashSet
    
    HashSet(Collection c)   - constructor initializes the hash set w/ the 
                            elements from 'c'
                            
    HashSet(int capacity)   - constructor builds an empty HashSet (HS) w/ 
                            init capacity of 'capacity'
                                - capacity automatically expands as 
                                elements are added to HS
                                
    HashSet(int capacigty, float fillRatio)
    
                            - constructor builds an empty HS w/ initial capacity of
                            'capacity' and initial fillRatio of 'fillRatio'
                            
                            FILLRATIO
                                - must be between 0.0 and 1.0
                                - determines how full the HS can be before it is
                                resized upward. 
                                - when size() > (capacity * fillRatio), hash set is
                                expanded. 
                                
                                this is proactive memory allocation, such that we 
                                expect mem to be used at a fast enough rate that we
                                want available slots for quicker usage
                                
### METHODS

    
    1   boolean                 add(Object o)
    
                                - adds 'o' to 'this' HS if not already present
                                
    2   void                    clear()
                    
                                - removes all elements from 'this' HS
                                
    3   Object                  clone()
    
                                - returns a shallow copy of 'this' HS
                                
                                (shallow copy means that the elements themselves
                                arent' cloned)
                                
    4   boolean                 contains(Object o) 
    
                                - returns true if 'this' HS contains 'o'
                                
    5   boolean                 isEmpty()
    
                                - returns true if 'this' HS contains no elements
                                
    6   Iterator                iterator()
    
                                - returns an iterator over the elements of 'this' HS            
                                
    7   boolean                 remove(Object o)
    
                                - removes 'o' from 'this' HS if it is present
                                
    8   int                     size()
    
                                - returns # of elements in 'this' HS 
                                (aka the 'cardinality' of the Set) 
                                

                    
                           