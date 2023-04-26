# 1.3 Risk of Threads

Safety      = "Nothing bad ever happens"

Liveness    = "Something good eventually happens"


## 1.3.1 Safety Hazards
- in the absence of sufficient synchronization
    - ordering of operations in multiple threads can be unpredictable
    - code that may work in single-threaded environment, may fail in 
    multithreaded environments.
    
    
        EX:
            @NotThreadSafe
            public class UnsafeSequence {
                private int value;
                
                /**
                    Returns a unique value
                */
                public int getNext() {
                    return value++;
                }
            }   

### Race Condition 
- a timing issue could result in 2 threads calling getNext() and getting the
same value.
- '++' (increment operator) is 3 separate operations
    - read value
    - add one 
    - write new value
- interleaving of concurrent operations are ARBITRARILY interleaved
    - it is possible for 2 threads to read the value at the same time. 
    
### Data Consistency
- Threads share same memory address space
    - when they run concurrently they can access/modify variables that
    other threads are using

PROS:
- makes data sharing easier

CONS:
- makes errors easier too. 
    - data can change unexpectedly
    - lack of "sequentiality"
- requires considerable coordination to ensure that threads don't step on
each other's toes.
    

### Synchronization
- process helps manage the coordination of multithreaded access
    - ensures that compiler, hardware and runtime optimizations don't
    introduce safety hazards

    EX
        // Using the synchronized modifier fixes the safety hazard
        // demonstrated above
        
        @ThreadSafe
        public class Sequence {
            @GuardedBy("this")
            private int value;
            
            public synchronized int getNext() {
                return value++;
            }
        }
        
## 1.3.2 Liveness Hazards
- liveness failures occur when a task/operation gets into a state that it 
can no longer move forward. 
    - infinite loop where the code that follows the loop is never exec'd
        - (This can happen in non-sequential code)
    - Deadlocks
    - Thread Starvation
    - Livelocks
    
### Timing
- one of the biggest challenges with any concurrency defect is that they
may only manifest under specific conditions that aren't easily reproducible
- timing is CRITICAL in concurrent systems


## 1.3.3 Performance Hazards
This is usually considered a "subset" of Liveness
- Liveness is concerned w/ something good eventually happens.
- Performance is concerned w/ ensuring that the good thing that happened is
sufficient
    - i.e. quick enough
- Types of performance problems
    - poor service time
    - responsiveness
    - throughput
    - resouce consumption
    - scalability
    
### Context Switches
A context switch is when the scheduler suspends the active thread
temporarily so another thread can run. 
- runtime overhead introduced in multi-threaded applications that incurs a 
performance penalty
- the overhead of context switching increases w/ the number of threads 
- the costs associated w/ context switches are
    - saving & restoring execution context
    - loss of locality
    - CPU time spent scheduling threads (rather than exec'ing them!)

### Synchronization
Synchronization occurs when threads must share data. 
- this kind of synchronization may require context switching
- it also includes other potential performance costs:
    - inhibition of compiler optimizations
    - flushing/invalidation of memory caches
    - create synchronization traffic on shared mem bus