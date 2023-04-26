# 13.3 Fairness

### Reentrant Lock Fairness Options
(Semaphores also offer nonfair/fair acquisition ordering)

#### Fair
- threads acquire fair locks in the order which they are
requested



#### Non-Fair (Default)
- permits *barging*
    - (threads requesting a lock can skip ahead in the queue
    of waiting threads if the lock happens to be 
    available when requested)
    - Reentrant Locks do NOT go out of their way to allow this behavior. They simply don't prevent
    threads from barging if they show up at the opportune moment. 
    
    
#### Thread Queuing w/ Lock Fairness
- newly requested threads w/ a FAIR lock are queued if the lock is held by another thread 
    - or if other threads are queued waiting for the lock
- newly requested threads w/ a NONFAIR lock are ONLY queued if the lock is held by another thread. 
    - by NOT queuing the lock if other threads are waiting, it is possible for the thread to "cut"
    in line.
    
    
    EXCEPTION:
        
        the polled tryLock ALWAYS barges (even for fair locks) 
        
       
        
##### Why wouldn't we want fairness??
- there is a performance penalty associated w/ the overhead of suspending/resuming threads 
    - (which is what happens when we queue them)


- nonfair locks provide a *statistical fairness guarantee*
    - a blocked thread will EVENTUALLY acquire the lock
        - less expensive
        - usually gives "GEP" -> Good Enough Performance
        
##### Why WOULD we want fairness? 
- Some algorithms rely on fair queuing to establish a degree of correctness that can not
otherwise be achieved by nonfair locks


- BEST USE CASE FOR FAIRNESS
    - when locks are held for a relatively long time.
    - when the mean time between requests is relatively long
    
    
    Generally, speaking, these use cases are less likely to provide the advantage
    that barging does when using NonFair locking
    
    - (See note on barging performance below). 
    - It is unlikely that there will be interleaving of thread contention for locks such that
    one Thread will request->acquire->use->release a lock before a resuming thread fully wakes up. 
    
    
    
### Fairness Performance Comparison
![Fairness Comparison](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/AdvancedTopics_4/ExplicitLocks_13/Images/Screen Shot 2021-01-07 at 5.34.42 PM.png)
- As the data shows, the fairness cost is almost 2 orders of magnitude greater for fair locks. 
    - not 2x... that's 100x. 


    As a general rule:
    
        The performance benefits of nonfair locks outweigh the benefits of fair queueing
        
        Don't pay for fairness if you don't need it.
        
        
#### Further Discussion

##### Why barging locks perform better than fair locks
- under heavy contention there can be a delay between when 
    - a suspended thread is resumed and when it actually runs. 
    
    
    Example:
    
        1.) Thread A holds a lock. 
        
        2.) Thread B requests the lock
        
        3.) B is suspended, because lock is held (busy) (Fair or NonFair)
        
        4.) A releases the lock
        
        (Best Case Scenario....) 
        - B is resumed, so it may try again.
        - If thread C requests the lock, and the task is short-lived enough, it is possible 
        that C can acquire the lock, use it and release it before B finishes waking up. 
        
            EVERYONE WINS:
            - B gets the lock no later than it otherwise would have
            - C gets the lock much earlier
            - throughput is improved, because C doesn't have to waste time in the queue. 
            
            

##### Intrinsic Locking and Fairness
- intrinsic locking (like **Reentrant Lock**) offers no deterministic fairness guarantee
- the "statistical fairness guarantee of *almost* all locking impls are good enough for
almost all situations"