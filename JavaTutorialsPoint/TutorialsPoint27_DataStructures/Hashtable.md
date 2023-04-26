# HASH-TABLE

    - provides a means of organizing data based on some user=defined key structure
    
    - "concrete" implementation of a Dictionary, re-engineered in Java 2 so that it
    also implements the Map interface
        - (Dictionary is obsolete) 
        
        - this means that HashTable is integrated into the collections Framework
        
    - similar to HashMap, BUT, HashTable is synchronized (thread safe) but less
    performant than HashMap. 
    
    USAGE:
    
        - specify an object to be used as a key, and the value that you
        want linked to that key
        
        - key is hashed and the resulting hash code is used as the index
        at which the vlaue is stored within the table
        
        
### CONSTRUCTORS

    1   Hashtable()             - default constructor instantiates Hashtable
                                class
                                
    2   Hashtable(int size)     - creates a hash table w/ initial size of 'size'
    
    3   Hashtable(int size, float fillRatio)
                                - creates a hash table w/ init. size of 'size'
                                and a fill ratio of 'fillRatio'
                                    - ratio must be between 0.0 and 1.0
                                    - determines how full the hash table can be
                                    before it is resized upward
                                    
    4   Hashtable(map < ? extends K, ? extends V> t)
                                - creates a Hashtable w/ the given mappings
                                
### METHODS

    1   void                clear()
                            - resets and empties hash table
                            
    2   Object              clone()
                            - returns dupe of 'this' hashtable
                            
    3   boolean             contains(Object value) 
                            - returns true if 'value' is equal to 
                            some value in 'this' hashtable
                            
    4   boolean             containsKey(Object key)
                            - returns true if 'key' is equal to a key
                            stored in 'this' hashtable
                            
    5   boolean             containsValue(Object value) 
                            - returns true if 'value' is equal to 
                            some value in 'this' hashtable
                            
    6   Enumeration         elements()
                            - returns an enumeration of the values in 
                            'this' hashtable
                            
    7   Object              get(Object key) 
                            - returns the object that contains the value
                            associated w/ the 'key'
                            (returns null if 'key' is not found)
                            
    8   boolean             isEmpty()
                            - returns true if 'this' hashtable is empty
                            
    9   Enumeration         keys()
                            - returns an enumeratino of the keys 
                            contained in 'this' hashtable
                            
    10  Object              put(Object key, Object value) 
                            - inserts 'key':'value' into hash table
                                - returns null if 'key' wasn't present at
                                time of insertion
                                - returns previous value associated w/
                                'key' if the 'key' was present in the 
                                hash table
                                
    11  void                rehash()
                            - increases size of hashtable and rehashes all
                            keys
                            
    12  Object              remove(Object key)
                            - removes key and its value. 
                            - removes value associated w/ the key
                            - rturns null if 'key' isn't present
                            
    13  int                 size()
                            - returns # of kv pairs in has table
                            
    14  String              toString()
                            - returns string equivalent of a hash table
                            
                            
                           