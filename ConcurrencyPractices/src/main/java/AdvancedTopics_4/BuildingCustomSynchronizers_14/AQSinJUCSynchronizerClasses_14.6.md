# 14.6 AQS in java.util.concurrent Synchronizer Classes

- many blocking classes in *java.util.concurrent* use AQS
    - **ReentrantLock**
    - **Semaphore**
    - **ReentrantReadWriteLock**
    - **CountDownLatch**
    - **FutureTask**
    
## 14.6.1 Reentrant Lock
- only supports *exclusive acquisition*
    - **tryAcquire**
    - **tryRelease**
    - **isHeldExclusively**
    
    
- uses synchronization state to hold lock acquisition count (c)
- maintains **owner** variable to hold the identity of the owning thread
   - only modified when current thread has
       - just acquired the lock
       - just about to release it
       
       
    NOTE: 
    - protected state-manipulation methods have the memory semantics of a VOLATILE
    read/write
        - this means that the changes made by ONE thread (to shared data) are 
        VISIBILE to all other threads.
    
    - ReentrantLock is careful when reading/writing owner field
        - only reads after calling getState
        - only writes after calling setState
    
    These two factors allow Reentrant Lock to piggyback onto the semantics of 
    the synchronization state
        - removes the need for further synchronization
        
        COOL. (This is better outlined in Section 16.1.4) 

- exploits several other AQS features
    - built-in support for multiple condition variables
    - built-in support for multiple *wait sets*
    - **Lock.newCondition** returns a new instance of **ConditionObject** (an inner class
    of AQS)
        
### tryAcquire 
- uses **owner** to differentiate between reentrant or contended acquisition attempts


    EXAMPLE: tryAcquire Impl from NonFair ReentrantLock
    
        protected boolean tryAcquire(int ignored) {
            final Thread current = Thread.currentThread();
            
            int c = getState();
            if (c == 0) {
                if (compareAndSetState(0, 1)) {
                    owner = current;
                    return true;
                }
            } else if (current == owner) {
                setState(c + 1);
                return true;
            }
            return false;
        }
        
#### Acquisition
- check lock state
    - if UNHELD
        - atomically attempts to set lock state to HELD using 
        **compareAndSetState** (Section 15.3)
            - sets state to held
            - confirms that the lock state hasn't changed since we last asked
    - else HELD
        - use **owner** to determine if we (the current thread) hold the lock
        - if we are OWNER
            - acquisition counter is incremented (defining this as a *reentrant* attempt)
        - else
            - acquisition fails and we have to wait.
    
### tryRelease
- checks **owner** field ot ensure that current thread owns the lock before 
allowing an **unlock** to proceed. 

## 14.6.2 Semaphore and CountDownLatch

    EXAMPLES:
    
        tryAcquireShared and tryReleaseShared (both from Semaphore)
        
        protected int tryAcquireShared(int acquires) {
            while (true) {
                int available = getState();
                int remaining = available - acquires;
                if (remaining > 0 || compareAndSetState(available, remaining)) {
                    return remaining;
                }
            }
        }

        protected int tryReleaseShared(int releases) {
            while (true) {
                int p = getState();
                if (compareAndSetState(p, p + releases)) {
                    return true;
                }
            }
        }

### Semaphore
- uses AQS synchronization state to hold the count of permits currently unavailable. 


#### tryAcquireShared
- computes no. of remaining permits
    - if NOT ENOUGH
        - returns a negative number indicating that the acquisition failed
    - else ENOUGH
        - uses **compareAndSet** to atomically reduce the permit count by the
        number of permits requested. 
            - if SUCCESS
                - returns positive number for a successful non-exclusive acquisition
                - the return value also encodes whether OTHER shared acquisitions
                 might succeed, allowing other waiting threads to be unblocked.
            - ELSE
                - returns a negative number indicating that the acquistion failed
- while loop
    - this terminates when
        - we run out of permits
        - (or) **tryAcquireShared** can atomically update the permit count to 
        reflect acquisition
    - (**compareAndSetState** can fail due to contention w/ other threads causing retry)
        - one of the 2 termination criteria above SHOULD become true in a reasonable number of retries.
                    

#### tryReleaseShared
- increases permit count
    - potentially unblocks waiting threads
    - retries until update succeeds
- return value indicates whether other threads might have been unblocked by release of permit(s)


### CountDownLatch
- (similar to **Semaphore**)
- AQS manages synchronization state that holds the current count
- **countDown**
    - calls **release** in AQS
        - decrements counter
        - unblocks waiting threads if counter == 0
- **await**
    - calls **acquire**
        - returns immediately if counter is zero
        - else it blocks.

## 14.6.3 FutureTask
- **Future.get**
    - has latch-like semantics
        - if some event has occurred, then threads can progress 
        - otherwise they're queued until that event happens
    - (an EVENT here is defined as the "completion/cancellation of the
    task represented by the **FutureTask**)
    
    
- uses AQS synchronization state to hold task status
    - (running/completed/cancelled)
- maintains additional state variables
    - holds result of computation or exception 
- if running, maintains reference to thread that is running the computation
    - allows **FutureTask** to interrupt the thread if the task is cancelled.
    
## 14.6.4 ReentrantReadWriteLock
- a single AQS based subclass manages both read and write locking
    - (the interface makes it seem like there are two separate locks)
    - READ-LOCK operations
        - use shared(non-exclusive) **acquire/release**
    - WRITE-LOCK operations
        - use exclusive **acquire/release**
    
- uses 16-bits of state EACH for write-lock count and read-lock count
    - (32 bits total)
    
### (Salient) AQS Internals
- AQs maintains a queue of waiting threads
    - tracks the type of access the thread requests (*exclusive/non-exclusive*)
    
- In **ReentrantReadWriteLock**
    - when lock becomes available
        - if thread at head of the queue was requesting WRITE  access, it gets it. 
        - if thread at head of queue was requesting READ access, all queued threads up to the
        first writer get it. 
        
    
    NOTE: 
    - this is the default impl for the queue of waiting thread in the AQS based 
    impl of ReentrantReadWriteLock
    
        - it does NOT allow you to choose a reader-preference/writer-preference policy
        (some ReadWriteLock impls do)
        
        - To offer it:
            - AWS wait queue would need to be 
                1.) not a FIFO queue
                2.) 2 queues.
                
    - Strict ordering is RARELY needed
        - if nonfair ReentrantReadWriteLock doesn't provide acceptable liveness,
        the fair version will. 