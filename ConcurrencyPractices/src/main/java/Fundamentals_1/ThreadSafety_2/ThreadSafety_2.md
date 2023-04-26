# Chapter 2 - Thread Safety

## Thread Safety Intro
"Writing thread-safe code is, at its core, about managing access to STATE, and
in particular to SHARED, MUTABLE STATE"
- Thread safety is about protecting DATA from uncontrolled concurrent access
    - depends on how an object is USED, not what it DOES
        - if an object can't change, or it isn't shared, then it's already
        safe. 
- It is easier/safer to design thread safety than to retrofit for it.
    - make code right, and THEN make it fast.

### Shared, Mutable State
- Object State
    -  data, stored in "state variables"
        - instance or static fields
- Shared
    - a variable that can be accessed by multiple threads
- Mutable
    - a variable whose state may change during its lifetime 
    
### Thread-Safe Code
1. State variables that are IMMUTABLE
1. State variables that are NOT SHARED across threads
1. Mutable, Shared State Variables that use SYNCHRONIZATION when accessed.

### Synchronization
- "Whenever more than one thread access a given state variable, and one of them
might write to it, they must all coordinate their access to it using SYNCHRONIZATION"
- Synchronization consists of:
    - synchronized keyword
    - volatile variables
    - explicit locks
    - atomic variables
    
### OOP + Thread Safety
- Good OOP techniques will help provide thread safety
    - encapsulation
    - immutability
    - clear specification of invariants
- The less access code has to variables:
    - the easier it is to ensure that the code uses proper synchronization
    - the easier it is to enumerate the conditions under which a variable
    can be accessed
    - the more likely that thread-safe design efforts will be comprehensively
    effective.
    