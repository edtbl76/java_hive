# GarbageCollector

## Responsibilities
- allocating memory
- ensuring referenced objects STAY in memory
- recovery memory used by objects that are no longer reachable from refs in executing code. 

LIVE OBJECTS
- objects that have a reference to another object

DEAD OBJECTS ("Garbage")
- objects that are no longer referenced by any "Live Object"

GARBAGE COLLECTION
- the process of finding/freeing (i.e. reclaiming) space used by DEAD OBJECTS

## TIMING OF GC
- usually determined by GC
    - entire heap or part of it is collected when it is FULL or reaches a threshold of percentage 
    of occupancy. 
    
