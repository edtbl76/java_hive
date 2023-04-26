# Sorted Map Interface

    - extends Map
    - ensures that entried are maintained in an ascending key order
    
    EXCEPTIONS
    
        NoSuchElementException          when no items are found in 'this' SortedMap
        ClassCastException              when an object is incompatable w/ elements in 'this' SortedMap
        NPE                             when attempt is made to use a 'null' and null is not allowed
        
        
### METHODS

    inherits all of the methods defined by Map interface and Collections interface
    
    AND
    
    1   Comparator                  comparator()
                                    - returns 'this' SortedMap's comparator
                                    - if natural ordering is used for 'this' SortedMap, null
                                    is returned
                                    
    2   Object                      firstKey()
                                    - returns first key in 'this' SortedMap
                        
    3   SortedMap                   headMap(Object end)
                                    - returns a new SortedMap for entries in 'this' SortedMap
                                    that have keys less than 'end'
                                    
    4   Object                      lastKey()
                                    - returns last key in 'this' SortedMap
                                    
    5   SortedMap                   subMap(Object start, Object end) 
                                    - returns a new SortedMap for entries in 'this' SortedMap
                                    that are between 'start' (inclusive) and 'end' (exclusive) 
                                    
    6   SortedMap                   tailMap(Object start) 
                                    - returns a new SortedMap for entries in 'this' SortedMap
                                    that are greater than or equal to 'start'
                                    
                                    