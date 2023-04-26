#Garbage Collection

automatic machine used to manage memory in the object lifecycle. 

EXPLICIT MEMORY MANAGEMENT IS NOT POSSIBLE IN JAVA

- objects are created by specific calls/initializations in the code
- Java runtime is responsible for recovering the memory once objects are no longer in use. 
    - once there are no REFERENCES to an object, it becomes ELIGIBLE to be freed. 
    - garbage collector goes through these "orphaned" objects and frees them
- ideally, GC operation occurs when a program is idle, but it MAY happen at any time.
- GC is GUARANTEED to occur if there is insufficient free memory on the HEAP to allocate a new 
object. 
    - THIS CAUSES A MOMENTARY STALL IN THE PROGRAM (due to the latency involved in carrying out the gc)
    
## NULLPOINTEREXCEPTION

- resource (memory)-like leak condition that may still occur if code still maintains a
REFERENCE to an object that isn't needed.

- NPE results when methods for an object that no longer exists may be called.