# 15.2 Hardware Support for Concurrency

### Pessimistic Locking
- this is a type of locking that assumes the worst
    - it doesn't proceed until you can GUARANTEE that other
    threads won't interfere, by acquiring the appropriate locks


    Exclusive Locks are pessimistic locks

### Optimistic Locking
- alternative approach designed for fine-grained operations
     - (usually more efficient)

#### Mechanism
- proceed with an update, hopeful that it can complete w/o interference


- relies on *collision detection* to determine if there has been interference/collison
from other parties during the update.
    - collision results in failure of the operation
    - (optional retries)


    it is easier (more efficient) to obtain forgiveness than to ask for permission.
    

### Multiprocessor CPUS and concurrency
- early processors had special instructions for managing concurrent access to shared/contended
variables
    - EX:
        - *test-and-set*
        - *fetch-and-increment*
        - *swap*
    - these instructions were used as the building blocks for *mutexes* that were subsequently 
    used to create more sophisticated concurrent objects. 
    
    
- Most (if not all) modern processors have atomic *read-modify-write* operations
    - EX:
        - **compare-and-swap**
        - **load-linked/store-conditional**
    - OS' and JVMs use these instructions to implement locks and concurrent data structures
    
    
## 15.2.1 Compare and Swap (CAS)
- most common approach in modern processors for atomic *read-modify-write* operations.
    - (PowerPC impl the same functionality using  a pair of instructions, 
    *load-linked* and *store-conditional*)

    
### Three Operands 
- V
    - memory location on which to operation
- A
    - expected old value
- B
    - new value
    

### Mechanism/Semantics
- read the value A from V
- derive new value B from A
- use CAS to atomically change V from A as long as no other thread has changed V to some 
other value
    - if V matches expected old value A
        - CAS atomically updates V to new value B
    - else
        - do nothing (V now reflects a modification from another thread)
- always returns V


    I think V should have the value A
    - if it does, put B there
    - otherwise, don't change it (but tell me I was wrong)

- OPTIMISTIC
    - proceeds with the update (hoping for success)
    - can detect failure if another thread has updated the variable since it
    was last read.

#### Compare-and-Set
- this is a variation on **compare-and-swap** that returns whether or not
the operation succeeded.
    
    
### Example of Semantics

    public class SimulatedCAS {
        
        // guarded by intrinsic lock
        private int value;
        
        public synchronized int get() {
            return value
        }
        
        public synchronized int compareAndSwap(int expectedValue, int newValue) {
            
            int oldValue = value;
            
            if (oldValue == expectedValue) {
                value = newValue;
            }
            return oldValue;
        }
        
        public synchronized boolean compareAndSet(int expectedValue, int newValue) {
            return (expectedValue == compareAndSwap(expectedValue, newValue));
        }
    }
- provides the basic "semantics" of CAS only

#### Shared Access
- when multiple threads attempt to update a variable at the same time using CAS
    - only one thread can win and update the variable
    - the rest lose
        - the advantage of CAS is that the threads aren't suspended as they would be
        if they failed to acquire a lock/permit/latch.
        - they fail and are notified of the failure
        
- Failed threads can be coded to perform an action in response to failure
    - retry
    - perform some other recovery operation
    - do nothing
        - in some NBAs a failed CAS might mean that another thread as performed the
        work that you are trying to perform
            - (EX: *linked queue algorithm* - Section 15.4.2)
            
            
- CAS avoids locks when impl'ing atomic *read-modify-write* via *collision detection* from
other threads.


## 15.2.2 A Nonblocking Counter


    Example: Thread Safe Non blocking Counter using CAS
    
        public class CASCounter {
            private SimulatedCAS value;
        
            public int getValue() {
                return value.get();
            }
        
            public int increment() {
                int v;
            
                do {
                    v = value.get();
                } while (v != value.compareAndSwap(v, v + 1));
            
                return v + 1;
            }
        }

### Increment
- fetch old value
- transform it to new value (by adding 1)
- use CAS to set new value
    - if CAS fails, the operation is immediately retried
    
    
        NOTE:
            Retrying can lead to livelock in cases of high contention
            - it is usually considered best practices to provide a waiter
            or backoff algorithm in these cases before performing a retry.

### Non Blocking vs. Retries
- while this algorithm doesn't block, if the counter is contended, it may require
several retries to update the counter while the variable remains contended by other
threads attempting to update it as well. 


    NOTE:
        
        This example is used to illustrate a concept. 
        
        In production code, if you need a counter or sequence generator, you can 
        use AtomicInteger/Long which provide atomic increment methods (among other
        atomic methods)
        
        
- WORST CASE
    - technically speaking, it is possible that other threads can continuously win 
    the CAS race, such that a thread attempting to update a variable is starved.
    - While this is rare, it is possible. 
        
### Lock-Based Counter vs. CAS-Based Counter
- CAS counter has more operations and a more complicated control flow.
    - also depends on "highly-complicated" CAS operation(s) 
    
    
- CAS significantly outperforms lock-based counters
    - even w/ a small (or no) contention
    - "Sometimes complexity pays off!!"
    - assuming moderate/low contention, CAS will succeed most of the time
        - hardware will accurately select the branch implied in the while loop
        - this minimizes the overhead of the more complicated aspects of CAS. 
        
        
    NOTE: 
        This is a factor in the performance of CAS.
        - as contention increases, so does the probability that the longer/complex code paths
        will be executed. 
        

- the fastest path for uncontended lock acquisition usually requires at least 1 CAS
and other lock-based care and feeding.
    - all things being equal, more work is actually taking place in a lock-based counter than a
    CAS-based counter.

### Optical Illusions (Language Syntax vs. Actual Work Done)
- In java, lock-based code tends to be more terse than CAS simulations, but the actual
work being performed by JVM + OS isn't. 
   
#### Locking Code Path
- traversal of fairly complex code path in JVM
- possibly:
    - OS-level locking
    - thread suspension
    - context switching
    

- BEST CASE
    - 1 CAS
        - Locks obfuscate CAS from sight, but doesn't save any cost of
        execution
        
        
#### CAS Code Path
- Directly calling a CAS from a program
    - NO JVM code
    - no systems calls
    - no scheduling activity.
    

- Although the code path appears longer in the application, its actually much shorter
when taking the JVM and OS into account. 


### DISADVANTAGES OF CAS compared to LOCKS
- forces caller to deal w/ contention 
    - retry, backoff or do nothing
    - Locks handle contention automatically by blocking the lock until available
- complexity of constructing surrounding algorithms
    - The terseness and simplicity of Locks makes them easier to use
    
    
## 15.2.3 CAS Support in the JVM
- low-level support exists to expose CAS operations on
    - int, long, object references
    - efficiency of the compilation performed by the JVM is driven by the
    underlying hardware. 
    - used by the atomic variable classes to provide an efficient CAS operation on numeric
    and reference types. 
        - **AtomicXXX** in *java.util.concurrent.atomic*
    
       
       NOTE: atomic variable classes are directly/indirectly used to
       implement most of the classes in java.util.concurrent.
    

- Platforms w/ CAS support allows the runtime to inline the operations into the
appropriate machine instructions
    - WORSE CASE
        - if a CAS-like instruction isn't available, the JVM can use a *spin lock*
        
  


    

    
    