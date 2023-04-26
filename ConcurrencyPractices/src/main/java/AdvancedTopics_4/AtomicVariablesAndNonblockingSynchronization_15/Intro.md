# Chapter 15: Atomic Variables and Nonblocking Synchronization
- many *java.util.concurrent* classes offer better performance and scalability than alternative
implementations that use **synchronized**
    - EX: 
        - **Semaphore**
        - **ConcurrentLinkedQueue**
        
    
- WHY? 
    - *atomic variables*
    - *nonblocking synchronization*
    
### Nonblocking Algorithms
- most recent research on concurrency is focused here. 
- use low-level atomic machine instructions (i.e. *compare-and-swap*)
instead of locks ot ensure data integrity under concurrent access
- much more complicated to design and impl than locking algorithms
- coordination occurs at a finer level of granularity
    - more significant reductino of scheduling overhead
    - they don't block when multiple threads contend for the same data. 


- IMMUNE to deadlock and other liveness problems. 
    - locking algorithms can't make progress if a thread sleeps/spins while holding a lock
    - nonblocking algorithms are immune to individual thread failures. 

#### Use Cases 
- used extensively in OS and JVM 
    - thread/process scheduling
    - garbage collection
    - implement locks and other concurrent data structures. 

#### Building Blocks
- in Java, efficient nonblocking algorithms can be constructed from *atomic variable* classes
    - EX:
        - **AtomicInteger**
        - **AtomicReference**
        
### Atomic Variables
- offer same memory semantics as *volatile* variables
- additional support for atomic updates
    - makes them useful for
        - counters
        - sequence generators
        - statistics gathering
- offers better scalability than lock-based alternatives.

#### Use Cases
- used as building blocks for NBAs
- can also be used as "better volatile variables"



