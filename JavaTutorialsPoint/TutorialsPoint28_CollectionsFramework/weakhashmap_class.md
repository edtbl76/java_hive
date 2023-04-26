# WEAK HASH MAP CLASS

    - implements 'Map' interface
    
    - stores only WEAK references to keys
        - allows a KV to be GC'd when its key is no longer referenced outside of
        the WeakHashMap
        
        - 'easesit way to harness power of weak references'
        
        - used for implementing REGISTRY-LIKE data structures, where the utility of
        an entry vanishes when its key is no longer reachable by any thread.
        
    WEAKHASHMAP v. HASHMAP
    
        - same functionality with ONE difference.
        
        - if Java memory manager ho longer has a strong reference to the object
        specified as a key, then the entry in the map will be removed. 
        
        
    WEAK REFERENCE
        - if the only refs to an object are weak, then the GC can reclaim the 
        object's memory at ANY TIME. 
            - normally, it will only be freed the next time the GC runs, which is
            when the system runs out of memory
            
            
### CONSTRUCTORS

    WeahHashMap()           - default constructor 
                                - initial capacity (16)
                                - load factor (0.75)
                                
    WeakHashMap(int initialCapacity) 
                            
                            - builds new empty WeakHashMap (WHM) w/ specified
                            'intialCapacity'
                            
    WeakHashMap(int initialCapacity, float loadFactor) 
                            
                            - builds new empty WeakHashMap (WHM) w/ specified
                            'intialCapacity' and 'loadFactor'
                            
    WeakHashMap(Map t) 
                            - constructor constructs a new WeakHashMap w/ 
                            same mappings as 't'
                            
### METHODS

    void        clear()         - removes all mappings from 'this' WeakHashMap (WHM) 
    
    boolean     containsKey(Object key) 
                                - returns true if 'key' in 'this' WHM
                                
    boolean     containsValue(Object value) 
                                - returns true if 'value' maps to one or more keys in
                                'this' WHM
                                
    Set     entrySet()          - returns collection-view of the mappings from 'this'
                                WHM
                                
    Object      get(Object key) 
                                - returns value mapped to 'key' from 'this' WHM, or null
                                if 'key' doesn't exist
                                
    boolean     isEmpty()       - returns true if 'this' WHM is empty
    
    Set     keySet()            - returns a set-view of the k4ys from 'this' WHM
    
    Object      put(Object key, Object value) 
                                - associates 'value' w/ 'key' in 'this' WHM
                                
    void        putAll(Map m) 
                                - copies all mappings from 'm' into 'this' WHM
                                    - NOTE, this will overwrite any existing
                                    mappings w/ the same key.
                                    
    Object      remove(Object key) 
                                - removes mapping for 'key' from 'this' WHM if
                                present
                                
    int     size()              - returns # of KV mappings from 'this' WHM
    
    Collection      values()    - returns collection-view of the values from 'this' WHM
    
    
    
    
                            
    