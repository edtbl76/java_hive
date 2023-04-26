# LINKED HASHSET CLASS

    - extends HashSet
        - adds no new members
        
    - maintains a LinkedList of the set entries, in the order in which they were
    inserted.
    
        - allows INSERTION-ORDER iteration over the set .
        - this means that when cycling through a LinkedHashSet, using an iterator, 
        the elements will be returned in the order that they were INSERTED. 
        
        - hashcode is used as index at which data is associated w/ the key stored. 
        
        
### CONSTRUCTORS

    HashSet()               - constructor builds a default HashSet
    
    HashSet(Collection c)   - constructor initializes the hash set w/ the 
                            elements from 'c'
                            
    LinkedHashSet(int capacity)   
                            - constructor builds an empty HashSet (HS) w/ 
                            init capacity of 'capacity'
                                - capacity automatically expands as 
                                elements are added to HS
                                
    LinkedHashSet(int capacity, float fillRatio)
    
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
        
         
       