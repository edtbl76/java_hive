# wait()
- tells calling thread to GIVE UP LOCK and GO TO SLEEP until some other thread enters
same monitor and calls NOTIFY()

- releases lock prior to waiting and reacquires lock prior to returning from wait() 
method. 
    - NOTE: the object lock isn't actually freed until the waiting thread 
    successfully (and completely) transitions into a state where it can 
    receive notifications. 
    - this prevents RACE CONDITIONS during the period that the wait() method
    releases and then reacquires a lock.
    
- prior to calling wait()
    - a method should always test the CONDITION while holding the synchronization lock
    - (there is no guarantee that the condition is set correctly)
    
- upon returning from wait()
    - the thread should always RETEST the condition to determine if it should actually 
    wait() again. 
    - (this is becasue another thread can ALSO test the condition and determine that
    a wait isn't necessary)
    
    EX:
    
        synchronized(lockObject) {
            while (!condition) {
                lockOBject.wait();
            }
            // take action here
        }