# TREEMAP CLASS

    - implements 'Map' interface using a 'tree' for storage. 
    
    - provides efficient means of storing KV pairs in sorted order and allows for
        rapid retrieval. 
        
    - see SortedMapInterface Demo for an example of a TreeMap
        
    TREEMAP v. HASHMAP
        - TREEMAP GUARANTEES that elements are sorted in ascending key order
        - HASHMAP is best effort that elements are sorted in ascending key order
        
        
### CONSTRUCTORS

    TreeMap()               - default constructor. 
                            - empty TreeMap (TM) that will be sorted using
                            natural order of its keys
                            
    TreeMap(Comparatort comp) 
                            - empty TM that will be sorted using 'comp'
                            
    TreeMap(Map m)          - TM initilized w/ entries frtom 'm', sortefd using
                            the natural order of its keys
                            
    TreeMap(SortedMap sm)   - inits a TM w/ entries from 'sm', which will be
                            sorted based on 'sm' sort order.
                            
                            
### METHODS

    void        clear()         - removes all mappings from 'this' TreeMap (TM)
    
    Object      clone()         - returns a shallow copy of 'this' TM
    
    Comparator comparator()     - returns the comparator used to order 'this' TM
                                - returns null if the keys are sorted based on
                                their natural search order
                                
    boolean     containsKey(Object key) 
        
                                - returns true if 'this' TM contains a mapping for 'key'
                                
    boolean     containsValue(Object value) 
    
                                - returns true if 'value' maps to one or more keys in 
                                'this' TM
                                
    Set         entrySet()      - returns a set-view of the mappings in 'this' TM
    
    Object      firstKey()      - returns first/lowest key from 'this' TM
    
    Object      get(Object key)   
                                - returns the value mapped to 'key' in 'this' TM
                                
    SortedMap   headMap(Object toKey) 
                                - returns a view of the elements from 'this' TM thaty
                                are < 'toKey'
                                
    Set     keySet()
                                - returns a set-view of the keys in 'this' TM
                                
    Object      lastKey()       - returns last/highest key from 'this' TM
    
    Object      put(Object key, Object value) 
    
                                - associates 'value' w/ 'key' in 'this' TM
                                
    void        putAll(Map map)     
                                - copies mappings from 'map' into 'this' TM
                                
    Object      remove(Object key) 
                                - removes mapping for 'key' from 'this' TM if
                                present
                                
    int     size()              - returns cardinality (# of kv pairs) from 'this' TM
    
    SortedMap   subMap(Object fromKey, Object toKey) 
                                - returns a map-view of the elements from 'this' TM
                                that are >= 'fromKey' and < 'toKey'
                                
    SortedMap   tailMap(Object fromKey)
                                - returns a map-view of the elements from 'this' TM
                                that are >= 'fromKey'
                                
    Collection  values()        - returns a collection view of the values 
                                contained in 'this' TM
                                
                                
                                
                                
                

    
                    
    