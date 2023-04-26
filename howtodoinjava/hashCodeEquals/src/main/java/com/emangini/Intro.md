# HASHCODE() AND EQUALS() METHODS

equals(Object otherObject)
- used to verify the equality of two objects
- default implementation simply checks the object references of two objects to verify their equality
- TWO OBJECTS ARE EQUAL IF AND ONLY IF THEY ARE STORED IN THE SAME MEMORY ADDRESS

hashCode()
- returns a unique integer for the object in runtime. 
- by default, int value is mostly derived from memory address of the object in heap. 
    - not mandatory
- used for determining bucket location when objects needs to be stored in some HashTable like data structure.

## CONTRACT between hashCode() and equals()

    EQUAL OBJECTS MUST HAVE EQUAL HASHCODES

- it is GENERALLY necessary to override the hashCode() method whenever equals() method is overriden

- whenever hashCode() is invoked on the same object multiple times during the execution of a Java 
application, the hashCode() must consistently return the same integer. 
    - provided that NO INFO used in equals() comparisons on the object is modified
    
- if 2 objects are equal according to the equals(Object) method, 
    - calling hashCode() on each of the 2 objects MUST produce the same integer result
    
- it is NOT required that if 2 objects are unequal to the equals(java.lang.Object) method, 
then calling hashCode() method on each of the 2 objects must produce distinct integer results. 
    - (BUT: distinct integer results for unequal objects will improve the performance of hash table
        due to collisions!) 
        
