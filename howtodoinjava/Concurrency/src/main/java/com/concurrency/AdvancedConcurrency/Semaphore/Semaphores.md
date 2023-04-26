# SEMAPHORE
- a semaphore is initialized w/ a number.. N
    - it can be decremented until it reaches 0. 
    - it can then be incremented until it reaches N.
    - counter must always be 0 <= n <= 5
- they make threads WAIT when the counter value == 0
    - they act as Locks(Monitors) w/ counter functionality
    
## MULTI-THREADING w/ SEMAPHORES
when a thread wants to access a shared resource (that is guarded by
a semaphore), it must "acquire" the semaphore.
- if counter > 0
    - semaphore decrements counter and allows access to shared resource
- else if counter == 0
    - semaphore puts thread to sleep until counter > 0

when threads are finished, they "release" the semaphore, which
increments the counter
    
## SEMAPHORE COUNTER
- the semaphore counter represents the capacity at which a resource is 
considered saturated. 

- if we initialize the counter to 100, then this means we'll allow 100 
threads to access this resource until the semaphore tells threads to wait()