# 15.1 Disadvantages of Locking

### Locking Definition
- coordinating access to SHARED state using a consistent locking protocol provides two 
important properties
    - MUTUAL EXCLUSION
        - ensures that the thread that holds the lock guarding a set of SHARED variables
    has EXCLUSIVE access to those variables
    - VISIBILITY
        - ensures that any changes made to those variables are visible to other threads that
        subsequently acquire the lock.
        
### Locking Mechanics
- uncontended lock acquisition/release
    - can be optimized fairly effectively by JVM on its own
    
    
- contended lock acquisition
    - JVMs might call in the cavalry (the OS)
    - *suspension* vs. *spin locking*
        - smart JVMs will use profiling data to decide adaptively between 
        suspension and spin locking based on the duration the lock was held on 
        previous acquisitions.
        - "nonsmart" JVMS have to suspend and resume. 
    
  
#### Issues
- suspend/resume pitfalls
    - lots of overhead
    - requires a lengthy interruption

- ratio of scheduling overhead to useful work is HIGH when
    - LOCK is FREQUENTLY contended
    - and the lock-based classes use fine-grained operations such as synchronized
    collection classes where most methods only contain a few operations.

### Volatile Variables vs. Locking
- Volatile variables are more lightweight
    - no *context switches* or *thread scheduling*
- provide same visibility guarantees. 
    
#### Limitations of Volatile Variables
- they can NOT be used to construct atomic compound actions. 
    - they can't be used in multi-variable invariants
        - one variable depends on another
        - one variable depends on its old value
        
- can't reliably be used to impl common tools
    - counters
    - mutexes
    
    
    EXAMPLE - Read-Modify-Write.
    
        ++i (incr operation) isn't an atomic operation. 
        
        This is THREE distinct operations
        
        1.) get current value of variable
        2.) add 1 to current value of variable
        3.) write update value. 
        
- NOTE: in order to ensure that we don't lose any updates, up until this point (in the book)
the only mechanism we've learned to impl **ReadModifyWrite** patterns is using locking. 


    EX: 
    
        public final class Counter {
            private long value = 0;
            
            public synchronized long getValue() {
                return value;
            }
            
            public synchronized long increment() {
                if (value == Long.MAX_VALUE) {
                    throw new IllegalStateException("counter overflow");
                }
                return ++value;
            }
        }
- EXPLANATION:
    - thread safe
    - performs fine if there is no contention
    - performs poorly if there is contention
        - context switch overhead
        - thread scheduling delays
  
    
- When locks are held for a very short amount of time, then being put to sleep is an expensive
penalty when requesting the lock at the "wrong time"


### Additional Locking Disadvantages
- threads are idle when waiting for locks
    - they can't do ANYTHING else but wait. 


- when a thread that holds a lock is delayed (page fault, scheduling delay etc.)
    - all other threads that need the lock have to wait
    - useful work is delayed. 

- if threads holding a lock are permanently blocked, then any other threads waiting on it
can't ever progress
    - (REASONS for block):
        - infinite loop
        - deadlock
        - livelock
        - "other" liveness failures.

#### Priority Inversion
- this is a performance problem that describes a scenario where a high-priority thread is waiting
on a lock that is held by a lower-priority thread. 
    - this results in the "effective downgrade" of the high-priority thread to the priority of the
    lower priority thread. 
    - "This brings a new meaning to the term "bringing yourself down to their level""
    

### Verdict on Locking
- considered a heavyweight mechanism (potential overkill) for fine-grained operations such 
as incrementing a counter. 