# 2.4 Guarding State With Locks
- Locks enable serialized access to the code paths they guard
    - this allows locks to be the foundation for building 
    systems that can guarantee exclusive access to shared state
- "Serializing access means that threads take turns accessing the object exclusively, rather than doing so concurrently"
    
### Compound Actions
- any compound (multi-step) operations that act on shared state
has to be made atomic to prevent RACE CONDITIONS
- EXAMPLES
    - Read-Modify-Write
        - incrementing a hit counter
    - Check-Then-Act
        - lazy initialization

#### Making Things Atomic
- Locks must be held for the ENTIRE DURATION of a compound action to
make that action atomic. 
    - if synchronization is used
        - wrapping the action in a sync'd block isn't enough
        - sync'd block/method must be used everywhere that the var
        is ACCESSED
    - when using locks to coordinate access to a var
        - the SAME LOCK must be used wherever that var is accessed
- RULE: "every shared mutable variable should be guarded by exactly one lock."
    - make it clear to maintainers which lock that is (Document it!!)
    
### Common Locking Convention(s)
- encapsulate ALL mutable state within an object
    - protect that object from concurrent access by synchronizing ANY code path that
    accesses mutable state using the object's intrinsic lock
- single variable invariant
    - guard that variable
        - (ensures only 1 thread at a time can access that variable)
- multi variable invariant
    - for every invariant that involves more than one variable
        - ALL variables involved in invariant must be guarded by SAME lock.

    


