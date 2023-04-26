# Chapter 7: Cancellation and Shutdown
- Starting tasks/threads is easy
- Stopping them isn't easy
    - most threads run to completion. This is easy because we don't have to 
    do anything. 
    - Cancelling threads means we have to actively stop it from
    running anymore
        - why? 
            - user cancelled an operation using the thread? 
            - app needs to shut down NOW!!!
    - Stopping Threads isn't easy because there isn't a mechanism for
    safely stopping threads while they are running
    
    
    Thread.stop()/Thread.suspend() was an attempt to provide that
    functionality, but it was deprecated
    
        DO NOT FUCKING USE THIS.
            
### Interruption
- cooperative mechanism that lets one thread ask another thread to
stop what it is doing. 
    - "cooperative approach" is required, because we usually don't
    want the task/thread/service to stop immediately
        - data structures can be left in an inconsistent state
        - they will need time to clean up work in progress, 
        release locks/access to any resources, and THEN terminate
    - the code running a task is going to be more able to assess
    the cleanup requirements than the code requesting cancellation
    
    

        
    

