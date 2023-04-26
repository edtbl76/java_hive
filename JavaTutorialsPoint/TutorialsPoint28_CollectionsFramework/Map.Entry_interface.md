# MAP.Entry interface

    - enables you to work w/ a map entry
    
    entrySet()
        - method from Map interface that returns a Set containing elements, each of which is a 
        Map.Entry object
        
        
### METHODS

    1   boolean             equals(Object obj) 
                            - returns true if 'obj' is a Map.Entry whose key and value are 
                            equal to that of 'this' Map.Entry
                            
    2   Object              getKey()
                            - returns the key for 'this' Map.Entry
                            
    3   Object              getValue()
                            - returns the value for 'this' Map.Entry
                            
    4   int                 hashCode()
                            - returns the hash code for 'this' Map.Entry
                            
    5   Object              setValue(Object v)
                            - sets value for 'this' Map.Entry to 'v'
                                - throws ClassCastException if 'v' isn't the correct type for
                                the map
                                - thows NPE if 'v' is null and the map doesn't permit null keys
                                - throws UnsupportedOperationException if map can't be changed
                                
                                
                         