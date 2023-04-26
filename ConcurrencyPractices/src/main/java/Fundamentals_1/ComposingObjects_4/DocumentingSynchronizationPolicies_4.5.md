# 4.5 Documenting Synchronization Policies

## Best Practices
- Document class's thread safety guarantees for its clients
- Document synchronization policy for its maintainers

## Synchronization Policy
- a strategy for ensuring the integrity of data in the face of concurrent access
- considered an element of program design and should be documented.

### Components
- which variables are VOLATILE
- which variables are guarded w/ locks
    - what kind of lock to use? 
- which variables should be immutable? 
- which variables should be confined to a thread
- which operations require atomicity? 

### Thread Safety Guarantees (per class)
- is it thread safe? 
- are callbacks made w/ a held lock? 
- are there specific locks that impact class behavior? 

