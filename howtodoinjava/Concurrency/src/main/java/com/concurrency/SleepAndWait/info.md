# SLEEP vs. WAIT

## Thread.sleep()
- pauses process for the amount of time we want
- places thread in NOT RUNNABLE state
- sleep can be "woke up " using Thread.interrupt();

- LOCK IS NOT RELEASED
    - NOTE: this means that if thread is in synchronized blocks or methods, other threads
    can't enter this block/method.
- used to introduce "pause on execution"

## Thread.wait()
- places the thread in a WAITING state
- in order for thread to execute, notify() or notifyAll() must be called. 

- LOCK IS RELEASED
- used for inter-thread communication


### COMPARISONS

wait()      
- Called on an object, current thread MUST SYNCHRONIZE on the lock object
- when SYNCHRONIZED, multiple threads access same Object 1 at a time. 
- has to release lock for other objects to have a chance to execute
- wake up conditions: notify(), notifyAll()
- used for multi-thread synchronization

sleep()     
- Called on a Thread; always currently executing thread. 
- when SYNCHRONIZED, multiple threads wait for sleep over of sleeping thread.
- keeps lock for at least 't' times if timeout is specified, or interrupt() called
- wake up is either expiration of timeout, or interrupt() call.
- used for TIME synchronization
