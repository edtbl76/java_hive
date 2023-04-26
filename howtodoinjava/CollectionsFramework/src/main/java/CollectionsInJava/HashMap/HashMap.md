# HashMap

## Hierarchy
HashMap extends AbstractMap (which implements Map interface)
implements Cloneable and Serializable interfaces. 

    DECLARATION:
    
    public class HashMap<K,V> extends AbstractMap<K,V> implements
        Map<K,V>, Cloneable, Serializable
        

## FEATURES
- no duplicate Keys
- allows multiple null values
- supports a single null key (no duplicates)
- Unordered Collection (no guarantees!)
- NOT THREAD SAFE
    - Collections.synchronizedMap(hashMap) is the synchronized version
    - you could use synchronized code block, but I hate that. 
- values can only be retrieved via the associated key
- ONLY SUPPORTS OBJECT REFERENCES
    - this means that primitives have to be stored via Wrapper classes
    

## INTERNALS
Hashing is a way to assign a unique code for any var/object after applying some
formula/algorithm on its properties. 
- each object in Java has it's hashCode, such that 2 equal objects must
consistently produce the same hash code. 

### HashMap.Entry
- Key/Value pairs are stored as an instance of an inner class. 
    HashMap.Entry
        - key is a final
        
    
    static class Entry<K,V> implements Map.Entry<K,V> {
        final K key;
        V value;
        
        Entry<K, V> next;
        final int hash;
        
        //=
    }
    
- all instances of Entry class are stored in an array 
    - 'transient Entry[] table'
- for each KV to be stored in HashMap, a hash is calculated using key's hash code
    - this hash value is used to calculate the index in the array for storing
    the entry object
    
COLLISIONS: (i.e. 2 keys are mapped to the same index)
    - a linked list is created to store all KV collisions
    
ACCESS
    - in order to get a value by key, the index location is used first
    - once at index, all elements are iterated in linkedlist. 
        - correct value is found by identifying key w/ equals() method.
        
### HashMap.put() INTERNALS.
key is checked for null
- if key is null, value is stored in table[0] position
- hashCode for null is always 0

hashCode() is called on the key 
- calculates the hash value
- hash value is used to calculate index in the array (map)
- index is where the ENTRY object is stored. 

NOTE: JDK devs pass objects hashcode to a separate hash() function to force
the hash value into the range of array index size. 

indexFor(hash, table.length) function is called  to calculate EXACT index to
store ENTRY

### RESOLVING COLLISIONS 
Two Unequal objects CAN SHARE A HASHCODE!!!! 
- Remember, the only requirement of hashing within a HashMap is that the 
hashCode() calculation on a single object is consistently the same. 
    - while uniqueness is desired, it is not a guarantee. 

BUCKET
- a bucket is a linked list that stores values that have collided at the same
index. 
    - Entry has a next() method that points to the next object in the chain.
    
When an ENTRY is placed at an index it checks if an entry already exists. 
    - if yes, it calls next() on that entry. it keeps calling next() until
    it finds a null. 
    
If we enter a NEW value object w/ the same key 
- ENTRY object has its hash calculated to be associated w/ an index. 
- one the index is located, if there is a collision, we begin iterating over
the linked_list. 
    - during iteration, HashMap calls equals() against the key object for each
    entry. 
        - NOTE: this tests for true equality. 
        - If equals() proves TRUE equality, then both keys are treated as
        the SAME OBJECT. 
            - here, the value is replaced inside the ENTRY structure. 
            
            
### HashMap.get() INTERNALS.
- same logic from put(). Since we know that a key must always have the 
same hashCode, we can locate it using he same logic as a put(). 
    - returns null if not exist. 
    
    
### INTERNALS NOTES
HashMap is a data structure to store entry objects. 
- it is an array named TABLE, that stores ENTRY object made up of K,V pairs.

Index Location are referred to as a BUCKET
- it holds the FIRST element of a linked list of ENTRY objects

Key Objects
- hashcode() is required to calculate the index location of ENTRY
- equals() method is used to maintain uniqueness of keys in map. 

Value objects hashCode() and equals() methods are NOT used