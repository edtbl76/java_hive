# MAP INTERFACE

    - maps unique keys to values
        - a 'key' is an object you use to retrieve a value at a later time
        
        
        - given a key and a value, you can store the value in a Map object
        - the value is retrieved using the key
        
    EXCEPTIONS
    
        NoSuchElementException          when no items exist in 'this' Map
        
        ClassCastException              when an object is incompatible w/ the elements in 
                                        'this' Map
        
        NullPointerException            when an attempt is made to use a null and null is not
                                        allowed in 'this' Map
                                        
        UnsupportedOperationException   when an attempt is made to change an unmodifiable Map
        

### METHODS

    1   void                clear()
                            - removes all KV pairs from 'this' Map
                            
    2   boolean             containsKey(Object k)
                            - returns true if 'this' Map contains a key 'k'
                            
    3   boolean             containsValue(Object v)
                            - returns true if 'this' Map contains a value 'v'
                            
    4   Set                 entrySet()
                            - returns a Set that contains the entries in 'this' Map. 
                                - the set contains objects of type MAP.ENTRY
                            - provides a 'set-view' of 'this' Map
                            
    5   boolean             equals(Object obj) 
                            - returns true if 'obj' is a Map and contains the same 
                            entries as 'this' Map
                            
    6   Object              get(Object k) 
                            - returns the value associated w/ key 'k'
                            
    7   int                 hashCode()
                            - returns the hash code for 'this' Map
                            
    8   boolean             isEmpty()
                            - returns true if 'this' Map is empty
                            
    9   Set                 keySet()
                            - returns a set that contains the keys from 'this' Map
                            - provides a set-view of the keys from 'this' Map
                            
    10  Object              put(Object k, Object v)
                            - puts an entry in 'this' Map, OVERWRITING any previous value
                            associated w/ 'k'
                            - returns null if 'k' didn't previously exist otherwise it
                            returns previous value of 'k'
                            
    11  void                putAll(Map m)
                            - puts all entries from 'm' into 'this' Map
                            
    12  Object              remove(Object k) 
                            - removes the KV pair associated w/ 'k'
                            
    13  int                 size()
                            - returns 3 of KV pairs in 'this' Map
                            
    14  Collection          values()
                            - returns a collection containing the values from 'this' map
                            this method provides a collection-view of the values in the map
                            
                            
                            
                    