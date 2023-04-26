# CAS (Compare and Swap Algorithm)

## OPTIMISTIC and PESSIMISTIC LOCKING

### PESSIMISTIC LOCKING
- SYNCHRONIZED keyword is PESSIMISTIC
    - asks first for a guarantee that no other thread will interfere between a given 
    operation (i.e. the LOCK)
    - only after this guarantee are you allowed to access the instance/method/object etc.
    
PROS:
- extremely safe

CONS:
- significant performance hit, because any other threads that want that resource have
to wait until other consumers can obtain a lock. 

### OPTIMISTIC LOCKING
- in this case, we proceed with caution, "hopefully we can complete it w/o any problems."
    - heavy reliance on:
        - collision detection
        - retriable tasks, compensating transactions (or some other "rollback" like 
        countermeasure)
        
## CAS ALGORITHM (COMPARE AND SWAP)
Example of an optimistic locking approach.

- compares the contents of a memory location to a given value
    - if (and only if) they are the same, the algorithm modifies the contents of 
    that memory location to the given value. 
    - single atomic operation
        - guarantees that new value is calculated based on up-to-date info. 
    - if the value had been updated by another thread in the meantime
        - write will fail. 
        
    - result of operation MUST indicate whether or not it performed the substitution
        - boolean (i.e. COMPARE AND SET)
        - returning the value read from the memory location (rather than the value 
        written to it.)
        
CAS PARAMETERS
1. memory location 'V' where value has to be replaced
1. Old value 'A' which was read by thread the last time
1. New value 'B' which should be written over 'V'

### CAS EXAMPLE:
V is memory location, storing the value '10'
- there are multiple threads who want to increment this value and use the
incremented value for other operations. 


CAS ALGORITHM

        if A = V;
            V = B;
        else
            operation failed
            return V;


STEP 1:

    Thread 1 and 2 want to increment it. 
    - they both read the value and increment it to 11
    
    V = 10, A = 0, B = 0
    
STEP 2:

    Thread 1 comes first and compares V w/ it's last read value.
    V = 10, A = 10, B = 11
   
    - Since A (the previous value) was 10, we can set the new value to B. 
    
STEP 3

    Thread 2 comes second and attempts the same operation
    V = 11, A = 10, B = 11;
    
    - in this case, we return the current value of 11, and FAIL, because V != A
    
STEP 4

    Thread 2 retries (it updates A w/ 11 in the previous step by returning the 
    stored value)
    
    V = 11, A = 11, B = 12. 
    

When multiple threads attempt to update the same THING concurrently using CAS
    - everybody who isn't first, loses. 
    - however, losers aren't punished by BLOCKING code. 
    - instead, they can retry or do nothing. 
    
    