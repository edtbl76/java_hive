# SERIALIZATION

    - object serialization is a mechanism where an object may be represented as 
    a sequence of bytes that includes the object's DATA as well as information about
    the object's TYPE and the TYPES OF DATA stored in the object. 
    
    - after a serialized object has been written into a file
        - it can be read from the file and DESERIALIZED 
            (this means that the type information and bytes that represent the object
            and its data can be used to recreate the object in memory)
            
    - process is JVM independent
        - object can be serialized on one platform and deserialized on an entirely 
        different platform
        
    
    ObjectInputStream and ObjectOutputStream
        - classes that are high-level streams that contain methods for object
        serialization and de-serialization
        
   
    SERIALIZATION
    
        ObjectOutputStream
        
            public final void writeObject(Object x) throws IOException
            
                - serializes 'x' and sends it to the output stream. 
                
    DESERIALIZATION
    
        ObjectInputStream
        
            public final Object readObject()  throwsd IOException, ClassNotFoundException
            
                - retrieves next Object out of the file stream and deserializes it
                - NOTE: the return value is an Object, so it must be cast to its
                appropriate data type.  
                
                
### RULES FOR SERIALIZING CLASSES

    - the class must implement 'java.io.Serializable' interface.
    
    - all of the fields in the class must be serializable. 
        - To determine if a Java Standard Class is serializable or not, 
        check the docs for the class. 
            - if the class implements java.io.Serializable, it is serializable. 
            - otherwise.. it isn't :) 
            
    - fields that are not serializable must be marked w/ the modifier 'transient'
    
  
### SERIALIZING AN OBJECT

    - ObjectOutputStream class is used to serialize objects. 
    - when serializing a file to an object, the standard convention in Java is to
    give the file a *.ser extension
    
            