# DeadLock Detection

## DEADLOCK
- a situation where (at minimum) two threads are holding a lock on some resource, 
both are waiting for the other's resource to complete it's task
- NEITHER of the threads are able to release the lock on the resource it is holding.

EX:
    
    Thread1 owns Resource A but is requesting Resource B
    Thread2 owns Resource B but is requesting Resource A. 
    
        - neither thread can release it's resource, because it needs the other
        to complete the critical code.