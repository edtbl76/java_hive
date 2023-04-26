# SortedSet Interface

    - extends Set Interface
    
    BEHAVIOR
        - takes a set and sorts it in ascending order. 
        
        
    EXCEPTIONS
    
        NoSuchElementException          when no items exist in 'this' set
        ClassCastException              when object is incompatible w/ elements in 'this' Set
        NullPointerException            when attempt is made to use null and null is not 
                                        allowed in the set.
                                        
                                        
### METHODS

    Inherits all methods from Set (and Collections) interfaces
    
    1   Comparator              comparator()
                                - returns 'this' SortedSet's comparator
                                - returns null if natural ordering is used
                                
    2   Object                  first()
                                - returns the first element from 'this' SortedSet
                                
    3   SortedSet               headSet(Object end) 
                                - returns a Sorted Set that includes elements from
                                'this' SortedSet that are before 'end'. 
                                - elements in returned SortedSet are also 
                                referenced by 'this' SortedSet
                                
    4   Object                  last()
                                - returns last element from 'this' SortedSet
                                
    5   SortedSet               subSet(Object start, Object end)
                                - returns a SortedSet that includes elements between 'start'
                                and 'end' from 'this' SortedSet. 
                                - elements in returned SortedSet are also 
                                reerenced by 'this' SortedSet
                                
    6   SortedSet               tailSet(Object start) 
                                - returns a SortedSet that includes elements from 
                                'this' Sorted Set that are greater than or equal to 'start'.
                                - elements in returned SortedSet are also
                                referenced by 'this' SortedSet
                                
      