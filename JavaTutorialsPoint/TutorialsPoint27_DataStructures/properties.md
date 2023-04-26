# PROPERTIES

    - subclass of Hashtable 
    - used to maintain lists of values in which the key and value are BOTH 
    Strings.
    
    - pervasive in Java
        - this is the type of object returned by System.getProperties() when
        obtaining EV
        
### INSTANCE VARIABLE

    - Properties defaults;
    
        - this variable holds a default property list associated w/ 
        a properties objects. 
        
### CONSTRUCTORS

    1   Properties()            - creates a Properties object that has no
                                default values. 
        
    2   Properties(Properties propDefault)
                                - creates Properties object that uses
                                propDefault for 'defaults' (but they are empty)
                                
                                
### METHODS

    - as a subclass of HashTable, Properties inherits all of its methods
    
    1   String              getProperty(String key) 
                            - returns value associated w/ 'key'
                            - returns 'null' if 'key' isn't in 
                            the properties list or defaults.
                            
    2   String              getProperty(String key, String defaultProperty)
                            - returns value associated w/ 'key'
                            - returns 'defaultProperty' is returned if the
                            key is neither in the list nor in the default
                            property list
                            
    3   void                list(PrintStream streamOut)
                            - sends property list to 'streamOut'
                            
    4   void                list(PrintWriter streamOut)
                            - sends property list to 'streamOut'
                            
    5   void                load(InputStream streamIn) throws IOException
                            - inputs a property list from 'streamIn'
                            
    6   Enumeration         propertyNames()
                            - returns an enumeration of the keys 
                            (includes keys found in default property list) 
                            
    7   Object              setProperty(String key, String value) 
                            - Associates 'value' w/ the 'key'
                            - returns previous value associated w/ 'key', or
                            null if the 'key' didn't exist
                            
    8   void                store(OutputStream streamOut, String description)
                            - after writing the string specified by description
                            the property list is written to 'streamOut'
                            
    