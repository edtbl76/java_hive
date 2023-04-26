# THROTTLING
Generically speaking, throttling is a controlled "slow down" of the flow of data/traffic in order
to prevent overruns or stalls due to resource saturation. 

FORMALLY: "Throttling is the capability of regulating the rate of input for a system where
output rate is slower than input."

## SEMAPHORE
- created w/ a number that must be equal to the maximum number of tasks in the blocking queue
at any given point in time. 

How It Works
- Before a task is executed, a lock in the semaphore is requested. 
- if the lock is acquired----> business as usual
- if NOT -----> keeps retrying until the lock is acquired. 
- lock is released to semaphore once task is completed (or dies)