# Identity HashMap

    - implements AbstractMap
    
    - similar to HashMap but it uses 'reference equality' when comparing the elements.
    
    - not a general purpose implementation
        - deliberately violates a Map's general contract, which mandates the use of
        the equals() method when comparing objects. 
        
    - USE CASE
        - rare cases wherein 'reference-equality' semantics are required. 
        - constant time [0(1)] performance for get() and put() 
            - assumes that system identity hash function dsitributes elements properly
            among the buckets
                (System.identityHashCode(Object)) 
                
                
### CONSTRUCTORS

    IdentityHashMap()               - new empty IdentityHashMap (IHM) w/
                                    default expectedMaximumSize (21) 
                                    
    IdentityHashMap(int expectedMaxSize) 
                                    - creates a new empty IHM w/ 'expectedMaximumSize'
                                    
    IdentityHashMap(Map m)          - creates new IHM w/ kv mappings from 'm'
    
### METHODS

    void        clear()         - removes all mappings from 'this' IdentityHashMap (IHM)
    
    Object      clone()         - creates a shallow copy of 'this' IHM
                                (keys and values are not cloned) 
                                
    boolean     containsKey(Object key) 
                                - returns true if 'this' IHM has 'key'
                                
    boolean     containsValue(Object value) 
                                - returns true if 'value' is mapped to one or more 
                                keys in 'this' IHM
                                
    Set     entrySet()          - returns a set-view of the mappings contained in 
                                'this' IHM
                                
    boolean     equals(Object o)
                                - returns true if 'this' IHM is equal to 'o'
                                
    Object      get(Object key)
                                - returns value mapped to 'key' from 'this' IHM
                                - returns null if 'key' doesn't exist
                                
    int         hashCode()      - returns hashcode value for 'this' IHM
    
    boolean     isEmpty()       - returns true if 'this' IHM is empty
    
    Set     keySet()            - returns an identity-bnased view of the keys
                                from 'this' IHM
                                
    Object      put(Object key, Object value) 
                                - associates 'value' with 'key' in 'this' IHM
                                
    void        putAll(Map t)   - copies mappings from 't' to 'this' IHM
                                - this will overwrite any mappings 'this' IHM had
                                for keys that also exist in 't'
                                
    Object      remove(Object key) 
                                - removes mapping for 'key' in 'this' IHM if present
                                
    int         size()          - returns # of kv mappings in 'this' IHM
    
    Collection      values()    - returns collection-view of values from 'this' IHM
            