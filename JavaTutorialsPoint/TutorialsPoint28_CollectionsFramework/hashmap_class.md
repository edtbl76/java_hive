# HASHMAP Class

    - implements Map interface using 'hashtable'
    
    BENEFITS
        - allows execution time of basic oeprations (i.e. get(), put()) to remain
        'time constant' for even large sets of data.
        
### CONSTRUCTORS

    HashMap()               - default hashmap constructor
    
    HashMap(Map m)          - initializes HashMap (HM) using elements of 'm'
    
    HashMap(int capacity)   - inits capacity of HM to 'capacity'
    
    HashMap(int capacity, int fillRatio)
                
                            - inits HM w/ 'capacity' and 'fillRatio'
                            
                                FILLRATIO (value between 0.0 and 1.0 that determines
                                how full the data structure can be before the next
                                upward resize) 
                                
### METHODS

    void        clear()         - removes all mappings from 'this' HashMap (HM) 
    
    Object      clone()         - creates a shallow copy of 'this' HM
                                (NOTE: in a shallow copy, the keys and values aren't
                                cloned) 
                                
    boolean     containsKey(Object key) 
    
                                - returns true if 'this' HM has a mapping for 'key'
                                
    boolean     containsValue(Object value) 
    
                                - returns true if 'this' HM maps one or more keys to 
                                'value'
                                
    Set         entrySet()      - returns a collection-view of the mappings in 
                                'this' HM
                                
    Object      get(Object key) 
                                - returns value mapped to 'key' in 'this' HM
                                - returns null if there is no mapping to 'key'
                                
    boolen      isEmpty()       - returns true if 'this' HM is empty
    
    Set         keySet()        - returns a set-view of the keys in 'this' HM
    
    Object      put(Object key, Object value) 
                                - associates 'value' w/ 'key' in 'this' HM
                                
    void        putAll(Map m) 
                                - copies mappings from 'm' to 'this' HM
                                    - NOTE: new mappings replace any existing mappings
                                    associated w/ keys that exist in both 'm' and 'this'
                                    HM
                                    
    Object      remove(Object key) 
                                - removes the mapping for 'key' in 'this' HM
                                
    int         size()          - returns the # of KV mappings in 'this' HM
    
    Collection  values()        - returns a collection view of the values 
                                contained in 'this' HM
                                
                                                    
