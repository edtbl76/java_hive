# DICTIONARY  OBSOLETE!!!!!!!

    - abstract class that defines a data structure for mapping keys to values
    
        - useful if you want to be able to access data by a named key rather
        than an integer-based index.
        
        - since it's abstract, it only provides the framework for a 
        key-mapped data structure rather than a specific implementation
        
        
        
    - operations very much like a Map
        - a Dictionary is essentially a list of key/value pairs
        
    - DONT USE THIS. USE THE MAP INTERFACE TO OBTAIN KEY/VALUE STORAGE IN JAVA
        
        
### ABSTRACT METHODS

    1   Enumeration             elements()
                                - returns an enumeration of the values 
                                contained in the dictionary
                                
    2   Object                  get(Object key)
                                - returns object that contains the value
                                associated w/ 'key'
                                - returns 'null' if 'key' doesn't exist
                                
    3   boolean                 isEmpty()
                                - returns true if dictionary is empty
                                
    4   Enumeration             keys()
                                - returns an enumeration of the keys
                                contained in the dictionary
                                
    5   Object                  put(Object key, Object value) 
                                - inserts 'key' and 'value' into dictionary
                                    - returns 'null' if key isn't already in
                                dictionary
                                    - returns previous value associated
                                    w/ 'key' if the 'key' already 
                                    exists. 
                                    
    6   Object                  remove(Object key) 
                                - removes 'key' and its 'value'. 
                                    - returns value associated w/ 'key'
                                    - if 'key' isn't present, returns null
                                    
    7   int                     size()
                                - returns the # of entrie3s in the dictionary
                                