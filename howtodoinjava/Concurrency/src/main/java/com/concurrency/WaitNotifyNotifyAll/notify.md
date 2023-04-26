# notify()
- wakes up a SINGLE THREAD that called wait() on the same object. 
    - NOTE: calling notify() does NOT give up a lock on a resource
    - tells a waiting thread that the thread can wake up. 
    - the LOCK isn't actually given up until the notifier's SYNCHRONIZED BLOCK 
    has completed
    
- if a notifier calls notify() on a resource, but the notifier needs to perform 10 seconds
of actions on the resource in its SYNCHRONIZED BLOCK
    - thread that was WAITING, will be blocking for at least another 10 seconds while the
    notifier executes its code and THEN releases the LOCK
    
- if notify() is called, and no threads are in a wait() state, then the notification is
lost, but nothing happens.
    
    
    
    EX:
    
        synchronized(lockObject) {
            
            // establish condition
            lockObject.notify();
            
            // any additional code if needed. 
        }

# WHO GETS NOTIFIED FIRST? 
- When the notify() method is called, there may be multiple threads waiting. 
- which thread gets notified first depends. 
    - Java specification doesn't have an explicit specification for this. 
    - JVM is a factor
    - scheduling/timing during program exec. 
    - there is no way to determine which thread is going to win, but one of them is
    going to.