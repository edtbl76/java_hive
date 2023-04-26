# GoodKey Design

MAIN REQUIREMENT
- retrieve the VALUE object back from the map without failure.

CONTRACT between equals() and hashCode()
- key's hashCode() is used primarily in conjunction to its equals() method
- when an object's state is modified, a flag is set so that the hashCode will be
recalculated the next time it is called. 
    - this is important because if the hashcode of a key object changes after we
    have put a KV pair in the map, it will be almost impossible to get that
    value  back from the map 
        - THIS IS A MEMORY LEAK
        
NOTE: due to the relationship between equals and hashCode, if you override when
you need to override the other. 
        
        
        
        
## HashMap key should be immutable
- This prevents the key from changing, which guarantees that the hashCode
will always be the same for a key object. 

- key must preserve the equals() -> hashCode() contract

NOTE: While immutability is recommended, it isn't required. 
