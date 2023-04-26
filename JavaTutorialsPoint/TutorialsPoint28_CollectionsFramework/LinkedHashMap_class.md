# LINKED HASH MAP CLASS

    - extends HashMap
    
    - maintains a LL of entries in the map (w/ INSERTION ORDER) 
        - when iterating over the map, elements are returned in the order
        they were inserted. 
        
    COOL FEATURE
        - linked hash maps can ALSO be returned in the order that THEY WERE
        LAST ACCESSED
        
### CONSTRUCTORS

    LinkedHashMap()             - default LinkedHashMap (LHM) 
    
    LinkedHashMap(Map m)        - initialized w/ elements from 'm'
    
    LinkedHashMap(int capacity) - initialized w/ 'capacity'
    
    LinkedHashMap(int capacity, float fillRatio)
                                - init'd w/ 'capacity' and 'fillRatio'
                                (see hashmap_class.md for definitions)
                                
    LinkedHashMap(int capacity, float fillRatio, boolean Order)
                                - same as above, BUT w/ the Order switch
                                    - if 'Order' is true, elements are stored 
                                    according to last access time
                                    - if 'Order' is false, elements are stored
                                    based on insertion order.
                                    
### METHODS

    void        clear()         - removes all mappings from 'this' LHM
    
    boolean     containsKey(Object key) 
                               - returns 'true' if 'this' LHM contains 'key'
                               
    Object      get(Object key) 
                                - returns value associated w/ 'key' from
                                'this' LHM
                                
    protected boolean       removeEldestEntry(Map.Entry eldest)
                                - returns true if this map should remove 'eldest'
                                
                                