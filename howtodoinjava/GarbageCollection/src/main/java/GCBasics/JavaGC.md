# Garbage Collection
Tracking down all the objects that are still used, marking everything else as "garbage"
- Java's GC is considered "automatic memory management"
    - devs don't have to designate objects as ready to be deallocatted
- GC runs on low-priority threads

## OBJECT LIFE CYCLE
Three Stages of an Object

### 1. Object creation 
- usually using 'new' keyword


    EX: Object obj = new Object();
    
- specific amount of mem allocated for storing creating object
    - amount of mem depends on JVM, ARCH
    
### 2. Object in use
- object is used by app's other objects
    - references pointing to object
- during usage (object life), it resides in mem and may contain references to other objects


### 3. Object termination
- GC system monitors objects, and as feasible, counts the no. of references to each object. 
    - once there are no more references to an object, it is "invisible" to running code, so it
    can be deallocated. 
    
## GC ALGORITHMS

