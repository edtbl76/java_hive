# notifyAll()
- wakes up ALL THREADS that called wait() on the same object. 
    - NOTE: highest priority thread will run first in MOST situations
        - (Not guaranteed) 
    - everything else is identical to notify()

- NOTE: notifyAll() tells ALL threads to wake up. 
    - one of the threads will "win", and then reacquire the object lock.
    - all of the other threads will then go back to waiting for the lock to be released.
    - (just like notify(), we can't determine which one wakes up first!) 
    - ONLY ONE THREAD EXECUTES AT A TIME. THIS IS CONCURRENCY.. NOT PARALLELISM.
    
- WHY? 
    - common question is "Why wake up all threads if only one can execute"
        - there might be multiple conditions that result in a wait() state
            - we can't control which thread gets the notification, its possible that
            a notification wakes a thread waiting for a different condition.
        - we allow a program to dynamically decide which thread should execute next. 
        (simpler code at the potential cost of unpredictability)
        - 
    
    EX: 
    
        synchronized(lockObject) {
            // establish condition
            lockObject.notifyAll();
            
            // additional code. 
        
        }