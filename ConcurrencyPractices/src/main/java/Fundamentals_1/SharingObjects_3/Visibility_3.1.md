# 3.1 Visibility

## Synchronization
- There is no guarantee that a "reading" thread will see changes
made by another thread in a timely basis w/o synchronization.
- without synchronization, the compiler/runtime can do whatever it
thinks is best to get the best outcome
    - this leads to some very bad outcomes.
- RULE: 
    - always use proper synchronization whenever data is shared across threads

(see Classes.NoVisibility)
- since synchronization isn't used properly (or at all) in this example, 
there is no guarantee that the values set in the variables that are
    - written by the main thread
    - (will be) read by the readerThread.

### Outcomes Due to No Visibility
- it is possible that a reading thread will loop forever, 
because it can't see a value change in a variable that
controls the "stop" condition
- reordering problem

#### Reordering
- this is a situation when a reader thread sees results of writes
out of order
    - in the case of the example above, the reading thread might
    see the stop condition for its loop before it actually 
    sees the value change. 
    - THIS IS BAD, because this is leads to operations being
    executed improperly, which can lead to safety/correctness
    failures
- There is NO guarantee that tasks in one thread will be 
performed in the order specified in code as long as 
the reordering is not detectible from within THAT thread
    - EVEN IF THE REORDERING IS APPARENT TO OTHER THREADS.
- the result of Reordering can be STALE DATA
    

## 3.1.1 Stale Data
- Stale data is when a thread reads out-of-date information due
to insufficient synchronization in concurrent programs.

### Preventing Stale Data
- Synchronization must be used EVERY TIME a variable is accessed in 
order to prevent stale data. 

### Impact of stale data
- erratic results
    - improper synchronization can result in correct data of some
    vars while others are stale
- Serious and confusing failures:
    - unexpected exceptions
    - corrupted data structures
    - inaccurate computations
    - infinite loops
    
### Examples
- NotThreadSafe
    - the value is accessed from get/set w/o synchronization
    - susceptible to stale data


    EX:
    
        @NotThreadSafe
        public class MutableInteger {
            private int value;
            
            public int get() {
                return value;
            }
            
            public void set(int value) {
                this.value = value;
            }
        }

- ThreadSafe
    - getter/setter is synchronized
    - value is guarded/thread-safe
    
    EX:
        
        @ThreadSafe
        public class SynchronizedInteger {
            @GuardedBy("this")
            private int value;
            
            public synchronized int get() {
                return value;
            }
            
            public synchronized void set(int value) {
                this.value = value;
            }
        }
        
## 3.1.2 Nonatomic 64-bit Operations

### Out-Of-Thin-Air Safety
- This is a safety guarantee that even when a thread sees stale data, that data is 
a value that was actually written by some thread
- the value is NOT A random value. 

#### Exceptions
- This applies to ALL variables other than 64-bit numeric variables (double and long) that
are NOT declared volatile. 
    - The Java Memory Model requires "fetch" and "store" operations to be atomic for everything
    but nonvolatile long and double variables. 
        - JVM treats these operations as two separate 32-bit operations
        - read/write can occur in different threads
            - operations against nonvolatile 64 bit data types can result in getting back the 
            32-bit fragments.
 
## 3.1.3 Locking and Visibility
- Intrinsic locking can be used to guarantee that one thread sees the effects of another in a
predictable manner
- without synchronization there can be no such guarantee


![Example](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/Fundamentals_1/SharingObjects_3/Images/Screen Shot 2020-08-24 at 2.19.26 PM.png)

- Locking is not just about mutual exclusion (Atomicity)
- Locking is also about memory visibility
    - to ensure that all threads see the most up-to-date values of shared mutable variables
        - reading and writing threads must synchronize on a COMMON lock
        
## 3.1.4 Volatile Variables
- These are a weaker for of synchronization than intrinsic locks
    - these ensure that updates to a variable are propagated predictably to other threads. 

### Internals
- when field is declared volatile
    - compiler/runtime are notified that the var is shared
    - operations should not be reordered w/ other memory operations. 
- volatile variables are NOT cached in registers and caches that are hidden from other processors
    - reads are ALWAYS the most recent
- performs no locking
    - executing thread can't block

### Extended Effect of Volatile Variables
- in terms of memory visibility
    - WRITING a volatile variable is like EXITING a sync block
    - READING a volatile variable is like ENTERING a sync block
- Example:
    - thread 1 writes to volatile variable X
    - thread 2 writes to X
    - the values of ALL vars visible to thread 1 before writing to X become visible to thread 2
    after reading X
    
### Best Practices
- use volatile vars only when they SIMPLIFY impl/verification of synchronization policy
- do NOT use them when verifying correctness would require convoluted/subtle reasoning about visibility
- common use case:
    - ensure visibility of 
        - their own state
        - the object they refer to
        - indicate that an important lifecycle event has occurred
            - EX: init(), shutdown()   
        - uses sometimes referred to as
            - completion, interruption, or status flags. 

- Example of common usage
    - used as a status flag to indicate an important event (exit)
    - this is volatile, because 'asleep' can be read/written to by multiple threads
        - without the volatile keyword, the change to 'asleep' might not be visible 
        to the thread that performs the CHECK of the CHECK-THEN-ACT
            - the loop would run indefinitely, and the app would never stop.
    - this is a common use case, because the locking code is typically more verbose. 
         
         
    EX: 
        
        volatile boolean asleep;
        // ... 
        
        while (!asleep) {
            coundSomeSheep();
        }
        
#### Criteria Under Which Volatile Variables Can be used
(NOTE: must meet all 3)
- when writes to variable do NOT depend on the current value
    - (or) it can be ensured that only a single thread ever updates the value
- the variable doesn't participate in invariants w/ other state variables
- locking is not required for any other reason while the variable is being accessed. 

### Downsides
- overuse of volatile can result in fragile code because it is often harder to read/understand than
code that uses locking.


### Locking vs. Volatile
- Locking can guarantee visibility AND atomicity
- Volatile variables only guarantee visibility



