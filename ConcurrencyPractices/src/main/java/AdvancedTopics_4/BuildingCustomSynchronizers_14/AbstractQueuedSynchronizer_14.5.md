# 14.5 AbstractQueuedSynchronizer

### Basic Operations
- variations on *acquire* and *release*

#### Acquisition
- state-dependent operation that can always block
- DEFINITIONS of *acquire*
    - **Lock/Semaphore**
        - acquire lock/permit
        - caller may have to wait until the synchronizer is a state where 
        the lock/permit can be acquired.
    - **CountDownLatch**
        - wait until latch has reached its terminal state
    - **FutureTask**
        - wait until the task has completed. 
        
#### Release
- not a blocking operation
- allows threads blocked in *acquire* to progress. 

### AQS + State-Dependent Classes
- AQS (can) manage state of synchronizer classes
    - single integer of state information
    - manipulated through *protected* methods
        - **getState**
        - **setState**
        - **compareAndSetState**
    - used to represent arbitrary state
        - **ReentrantLock** 
            - represents the count of times the owning thread has acquired the lock
        - **Semaphore**
            - represents the no of permits remaining.
        - **FutureTask**
            - represents state of the task
                - not yet started, running, completed or cancelled
            
            
- Synchronizers manage their own state too
    - **ReentrantLock** tracks current lock owner
        - (used to differentiate between reentrant and contended lock-acquisition 
        requests)
        
### Exclusivity and Acquisition

    Example - Canonical Forms for Acquisition and Release in AQS
    
        boolean acquire() throws InterruptedException {
            while (state doesn't permit acquire) {
                if (blocking acquisition requested) {
                    - enqueue current thread if not already queued
                    - block current thread
                } else {
                    - return failure
                }
            }
            - possibly update synchronization state
            - dequeue thread if it was queued
            - return success
        }
        
        void release() {
            - update synchronization state
            if (new state may permit a blocked thread to acquire) {
                - unblock one or more queued threads
            }
        }
        
- EXCLUSIVE acquistion
    - **ReentrantLock**
    - synchronizer subclasses that support this type of acquisition impl the
    following protected methods:
        - **tryAcquire**
        - **tryRelease**
        - **isHeldExclusively**
        
        
- NON-EXCLUSIVE/(Shared) acquisition
    - **Semaphore**, **CountDownLatch**
    - synchronizer subclasses that support shared acquisition impl the following protected
    methods:
        - **tryAcquireShared**
        - **tryReleaseShared**
        
        
- AQS methods for acquisition
    - **acquire/release**
    - **acquireShared/releaseShared**
    - (these methods call the *try* versions mentioned above in the
    synchronizer subclass to determine if the operation can proceed)
    
    
- The synchronizer subclass uses the state methods mentioned far above to 
examine/update state according to the acquire/release semantics of the given 
subclass (i.e. Lock/Semaphore/Latch/etc.)
    - uses the *return* status to inform base class whether or not the **acquire/release**
    was successful. 
    
    
- subclass return status
    - **acquire**
        - NEGATIVE number = acquisition failed
        - ZERO = synchronizer acquired EXCLUSIVELY
        - POSITIVE number = synchronizer acquired NON-EXCLUSIVELY
    - **tryRelease/tryReleaseShared**
        - returns *true* if release unblocked threads that were 
        attempting to acquire the synchronizer
    

### Acquisition Components

#### Part 1: State Evaluation
- synchronizer evaluates whether or not the current state permits acquisition by
a requesting thread.
    - YES:
        - thread proceeds w/ acquisition
    - NO:
        - acquisition waits or fails.
       
        
- decision determined by synchronizer semantics
    - (i.e. lock can succeed if it isn't held, latch can succeed if its in terminal state)
    
#### Part 2: Updating (Synchronizer) State
- when a thread acquires a synchronizer it may possible affect the ability of
other threads to acquire it. 
    - LOCK
        - acquiring a lock changes it's state from "available" to "not available"
    - SEMAPHORE
        - acquiring a permit FROM a semaphore decrements the available permits
    - LATCH
        - acquiring a latch doesn't change it's state at all. 

## 14.5.1 A Simple Latch

    public class OneShotLatch {
        private final Sync sync = new Sync();
        
        public void signal() {
            sync.releaseShared(0);
        }
        
        public void await() throws InterruptedException {
            sync.acquireSharedInterruptibly(0);
        }
        
        private class Sync extends AbstractQueuedSynchronizer {
        
            protected int tryAcquireShared(int ignored) {
            
                /* 
                    Succeed if latch is open (state == 1), else DIE
                    
                    - note that we are using the AQS state mutators/modifiers, so 
                    AQS is managing the state, rather than the synchronizer itself.
                */
                return (getState() == 1) ? 1 : -1;
            }
            
            protected boolean tryReleaseShared(int ignored) {
                // Open the Latch
                setState(1); 
                
                // return true so that other threads can acquire
                return true;
                                
            }
        }
    }
### Explanation
- This is a binary latch impl'd w/ AQS
    - latch state is managed by AQS rather than itself
        - (initial state is 0 -> CLOSED)
- 2 public methods for acquisition/release
    - **await**
        - calls **acquireSharedInterruptibly**
            - calls **tryAcquireShared**
                - this returns a value that indicates whether or not acquisition can proceed. 
                - If the latch is open, then it returns a positive value (success)
                    - once **signal** is fired, then any threads in the wait queue will acquire
                    the latch and succeed. 
                - If the latch is still closed, then it returns a negative number (fail)
            - **acquireSharedInterruptibly** propagates success up, but it interprets failure
            as a signal to enqueue the thread on the thread-wait queue.
    - **signal**
        - calls **releaseShared** -> **tryReleaseShared**
            - unconditionally sets the latch state to open
            - when considering latch semantics, the return value indicates that the
            latch is in its terminal state, and therefore threads may progress. 
            
#### Delegation > Extension
- We impl'd AQS via "delegation" rather than by extension
    - extension undermines the simplicity of the 2 method interface
    - public methods of AQS prevent callers from corrupting an escaped latch state from the
    delegated private inner class
        - remember! Hide your state!!!!!!
        
        
    NOTE: All synchronizers in java.util.concurrent that implement AQS do so through
    delegation
    
        NONE of them extend AQS directly

#### Verdict
- this is a completely working (albeit limited) implementation of a latch-based synchronizer. 
    - in 20 fucking lines of code
    
- missing features that are pretty easy to toss in 
    - time-based acquisition.
    - peek at latch state before attempting to acquire. 

        
        