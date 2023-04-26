# 16.1 What is a Java Memory Model and Why Would I Want One? 

### Memory Model

    Assume one thread assigns a value to "aVariable"
    
        aVariable = 3

- a memory model addresses the question
    - "Under what conditions does a thread that reads
    **aVariable** see the value 3?"
    

#### In a multi-processor system without synchronization...    
- There are MANY reasons why a thread might not immediately (or ever) see the 
results of an operation in another thread.
    - Compilers might generate instructions in the wrong order than the intended (or obvious) order
    provided by the source code
    - variables might be stored in registers instead of memory
    - procs might execute instructions in parallel or out of order
    - caches may vary the order in which writes to variables are committed to main memory
    - values stored in processor-local caches might not be visible to other processors.

    
    These are SOME factors that can prevent a thread from seeing the most 
    up-to-date value for a variable
    - can cause memory actions in other threads to appear to happen out of order
    
        "IF YOU DONT USE PROPER SYNCHRONIZATION"
        
#### Single-Threaded Environment
- all the previous mentioned issues only speed up execution
    - contention and interleaving aren't possible in a single-threaded environment.


- JLS (Java Language Specification) requires the JVM to maintain *withinthread as-if-serial semantics*
    - as long as the program has the same result as if it were executed in program order in a strictly
    sequential enviornment
        - all of the optimizations/compiler "games" above are allowed. 
        
        
- BENEFITS
    - beyond improvements in clock rates, many improvements in computing performance are
    derived from compiler optimizations
        - increased parallelism
        - pipelined superscaler execution units
        - dynamic instruction scheduling
        - speculative execution
        - sophisticated multilevel memory caches
    

- Compilers have had to become more sophisticated to keep pace with innovation of processors
    - instructions are rearranged to provide optimal execution
    - more sophisticated global register-allocation algorithms
    
    
- Clock rates become harder to increase (economically), so hardware parallelism will continue
to increase


#### Multithreaded Environment
- we can't provide the "illusion" of sequentiality w/o substantial performance cost.
    - each time thread in a concurrent app are performing their own isolated work
    - excessive coordination between threads introduces overhead that slows down the app. 
        - the more overhead we introduce, the less benefit parallelism provides. 
        
    
- The ONLY time we coordinate work between threads is when they share data. 
    - The JVM relies on the program to ID "touch points" for shared data via the use
    of *synchronization*
    
#### JMM and Concurrency
- The Java Memory Model provides MINIMUM guarantees that the JVM has to make about the what/when
concerning writes to variables becoming visible to other threads


- The JMM was designed to balance:
    - the need for predictability and simplicity of program development
    - (vs/with) the practical reality of impl'ing high-performance JVMs across the vast array of
    processor architectures. 
    
## 16.1.1 Platform Memory Models


### Cache Coherence
- Shared-Memory multiprocessor architecture
    - each CPU has its own cache that is periodically reconciled w/ main memory
    - IDEAL cache coherence would be to ensure that every CPU is aware of what every other 
    CPU is doing at all times
        - very expensive


- procs provide varying degree of *cache coherence*
    - (some) provide minimal guarantees that allow different CPUs to see different values
    at the same memory location virtually at any moment in time.
    - The OS, compiler, runtime (and sometimes the APP) have to makeup the difference between:
        - what the hardware provides
        - (and) what thread safety requires.
        

#### Performance Improvements
- Ideal cache coherence isn't practical, and most of the time the information isn't necessary
    - relaxing memory coherency guarantees allows the processor to provide better performance
    

### Memory Model
- a conceptual aspect of a processor architecture that tells programs what guarantees can be
expected from the memory system
    - includes *memory barriers/fences* 
        - (specific instructions required to get additional memory coordination guarantees
        when sharing data)
        

### Java Memory Model (JMM)
- the JMM is provided as a means of abstraction to protect Java developers from having to know
the differences between memory models across different processor architectures
    - the JVM is the vehicle of abstraction, inserting *memory barriers* at the 
    appropriate places. 
    
    
- the use of *memory barriers* is to explicitly specify that data shouldn't be shared across threads
    
    
- one of the main advantages of the JMM (and the JVM's heavy lifting) is that developers don't need
to understand the semantics for *memory barriers* of different processor architectures. 
    - the proper use of *synchronization* informs the JVM that we want to prevent sharing data between
    threads, and the JVM inserts the necessary statements to 
    
    
#### Sequential Consistency (NO!)
- this is a (helpful, but unrealistic) mental model for program execution
  
    
    Operations that occur in a program happen in a single order
        - regardless of what CPU they operate on
        
    Each Read will see the last write in the execution order to that variable
        - by any CPU. 
        
    
    See the von Neumann model
        - classic sequential computing model that only intends to be a VAGUE
        APPROXIMATION of how modern multiprocs act.

- REALISTIC EXPECTATIONS
    - many software devs assume *sequential consistency*
    - but neither the JMM or any other modern multiprocessor offers it. 
    - modern compilers and multiCPUs (that support shared-memory) can do some
    weird things when data is shared across threads
       
## 16.1.2 Reordering
- reordering at the memory level makes programs behave unexpectedly

### Refresh Race Conditions and Atomicity Failures
- (See Chapter 2)


- "unlucky timing" can occur when a scheduler interleaves operations that leads to incorrect
results in apps that don't properly use *synchronization*

- JMM can allow *reordering*
    - this is the phenomenon when actions appear to execute in different orders from the perspective of
different threads. 
    - (this also can make some operations appear delayed)
    - this makes reasoning about ordering in the absence of *synchronization* extremely complicated
    (which is probably why "they" created it!)

### Example
- (See Examples.PossibleReordering)
- The example is a basic app using 2 threads that share data w/o any *synchronization*

- Possible Results
    - (1,0)
    - (0,0)
    - (0,1)
    - (0,0)

- Possible Reasons 
    - **threadOne** could run to completion before **threadOther** starts
    - **threadOther** could run to completion before **threadOne** starts
    - the actions of the two could be interleaved.


- The actions in each thread have no data dependency relationship on each other
    - this is the justification for synchronization or coordination. 
    - w/o some device to enforce an invariant, tasks can be executed out of order. 
    
    
    NOTE:
        - In some scenarios, even if tasks are executed IN ORDER, 
        the timing by which caches are flushed to main memory can make it appear
        from the "other" thread, that the assignments in the original thread occurred in
        the opposite order. 
    
    
![Figure 16.1](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/AdvancedTopics_4/TheJavaMemoryModel_16/Images/Figure16.1.png)
- This is a good visual representation of how reordering can impact the result
- (This is the strange case of (0,0))


### Benefits of Synchronization
- makes it easier to reason about ordering of execution in multi-threaded applications by inhibiting
the compiler, runtime, and hardware from reordering memory operations in ways that violate
visibility guarantees provided by the JMM

        NOTE: 
        
            On MOST major CPU architectures
            - the memory model is strong enough that the perf cost of a
            volatile read is similar too a nonvolatile read
            
            
## 16.1.3 The JMM in 500 words or less. 

### Actions
- JMM is specified in terms of *actions*
    - reads/writes to variables
    - locks/unlocks of monitors
    - starting/joining w/ threads. 


### Happens-Before
- a partial ordering defined on all actions in a program.
    
    
    Given two actions, A and B that may occur in the same or different threads. 
    
        To guarantee that the thread executing action B can see the results of action A, there
        must be a HAPPENS-BEFORE relationship between the two operations
        
- The JVM reorders actions as it deems necessary UNLESS there is a *happens-before*
relationship between 2 actions 


#### Partial Ordering
- (mathematical concept) a relation on a set that is
    - asymmetrical
    - reflexive
    - transitive
  
    
- but for any 2 elements X and Y, ir doesn't need to be the case that
    - X relates to y or Y relates to X. 
  

- partial ordering is used to express preferences.
    - I like Green more than Yellow
    - I like Blue more than Orange
    - We have no idea what the preference is between Green and Blue.
    
    
#### Data Race
- occurs when
    - variable is read by multiple threads
    - same variable is written to by ONE thread
    - reads + writes aren't ordered by *happens-before*
    

- a *correctly synchronized program* can be defined as a program that has no
*data races*
    - proper *synchronization* exhibits *sequential consistency*
        - "all actions appear to have a fixed global order"
        
        
#### Rules for Happens Before

##### Program Order Rule
- each action in a thread *happens-before* every action in that thread that comes later in the
program order.


##### Monitor Lock Rule
- An unlock on a monitor lock *happens-before* every subsequent lock on that same monitor lock
    - lock/unlock operations on explicit **Lock** objects have the same memory semantics as
    *intrinsic locks*


##### Volatile Variable Rule
- A write to a *volatile* field *happens-before* every subsequent read of that same field
    - reads/writes of atomic variables have the same memory semantics as 
    *volatile* variables
    

##### Thread Start Rule
- A call to **Thread.start** on a thread *happens-before* every action in the started thread


##### Thread Termination Rule
- any action in a thread *happens-before* any other thread detects that thread has terminated.
    - either by
        - successful return from **Thread.join**
        - (or) returning false from **Thread.isAlive**


##### Interruption Rule
- A thread calling interrupt on another thread *happens-before* the interrupted thread detects
the interrupt
    - either by
        - throwing **InterruptedException**
        - invoking **isInterrupted** or **interrupted**
        

##### Finalizer Rule
- The end of a constructor of an object *happens-before* the start of the finalizer for that object



##### Transivity

    If 
        A happens-before B and
        B happens-before C
        
    Then
    
        A happens-before C
        

#### Partial vs. Total Ordering
- Partial Ordering
    - Actions
- Total Ordering
    - synchronization actions
        - lock acquire/release
        - reads/writes of *volatile* variables
        
        
        
- since *synchronization* actions are totally ordered, it makes sense to 
describe *happens-before* relationships in term of "subsequent" lock acquire/release or
read/write of *volatile* variables

 
    
#### Happens-Before and JMM
![Figure 16.2](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/AdvancedTopics_4/TheJavaMemoryModel_16/Images/Figure16.2.png)
- Demonstrates *happens-before* relationship between 2 threads that synchronize on a common lock
    - All actions on thread A are ordered by Program Order Rule
    - All actions on thread B are ordered by Program Order Rule
    - Since A releases Lock M and B "SUBSEQUENTLY" acquires M
        - all actions in A before releasing the lock are ordered BEFORE the actions in 
        B after acquiring the lock. 
- If 2 threads synchronize on different locks, there is no *happens-before* relationship between the
2 threads
    - ordering is not guaranteed between the 2. 
    

## 16.1.4 Piggbacking on synchronization

### Happens-Before Gravity
- Due to the strength or gravity of the *happens-before* relationship, it is possible to 
"piggyback" on the visibility properties of an existing *synchronization*


- This usually requires combining Program Order Rule w/ some other ordering rule to access a variable
that isn't otherwise guarded by a lock. 
    - (usually) Monitor Lock or Volatile Variable Rule
    
    
    NOTE: 
    - This is a technique that is very sensitive to the ordering in which statements occur
    
    - This means it is VERY VERY VERY FRAGILE. 
    
- This should only be used when it is absolutely necessary to squeeze every last drop out of 
performance critical classes (like **ReentrantLock**)

### AQS in FutureTask

    Inner class of Future Task that illustrates synchronization piggybacking
    
    
    private final class Sync extends AbstractQueuedSynchronizer {
        private static final int RUNNING = 1;
        private static final int COMPLETE = 2;
        private static final int CANCELLED = 4;
        
        private V result;
        private Exception exception;
        
        void innerSet(V value) {
            while (true) {
                int state = getState();
                
                if (ranOrCancelled(state)) {
                    return;
                }
                
                if (compareAndSetState(state, COMPLETE)) {
                    break;
                } 
            }
            
            result = value;
            releaseShared(0);
            done();
            
            V innerGet() throws InterruptedException, ExecutionException {
                acquireSharedInterruptibly(0);
                
                if (getState() == CANCELLED) {
                    throw new CancellationException();
                }
                
                if (exception ! null) {
                    throw new ExecutionException(exception);
                }
                
                return result;
            }
        }
    }


- impl of protected AWS methods in **FutureTask** provide an example of *piggybacking*
    - AQS uses an int of synchronizer state that **FutureTask** leverages to store task state
        - (running, completed, cancelled.)
    - However, **FutureTask** also manages its own variables
        - (ex. result of computation performed by task represented by **FutureTask**)
        
        
- **FutureTask** was built to ensure that 
    - a successful call to **tryReleaseShared** always *happens-before* a SUBSEQUENT call to
    **tryAcquireShared**
    
    
### Piggybacking
- This technique is called *piggybacking* because 
    - it surfs on an existing *happens-before* relation/ordering that was created to ensure 
    visibility of object X
    - rather than creating a NEW *happens-before* ordering specifically to publish that object X.
    
#### When to use it
- in most cases, you shouldn't, because it is very fragile. 


- Reasonable usage is when a class commits to a *happens-before* ordering between methods
as part of tis specification


### Happens-Before Orderings Guaranteed by Class Library

#### Blocking Queue
- safe publication using **BlockingQueue** is piggybacking
    - When one thread enqueues an object and another thread SUBSEQUENTLY dequeues it
    - this constitutes *safe publication* because it is guaranteed that there is sufficient 
    internal synchronization in a **BlockingQueue** impl to ensure that
    enqueue *happens-before* dequeue.
    

#### Other
- put() an item in a thread-safe collection *happens-before* another thread performs get() from
that collection

- counting down on a **CountDownLatch** *happens-before* a thread returns from **await** on that
latch

- releasing a permit to a **Semaphore** *happens-before* acquiring a permit from the same **Semaphore**

- Actions performed by the task represented by a **Future** *happens-before* another thread
can successfully return from **Future.get**

- Submitting a **Runnable/Callable** to an **Executor** *happens-before* the task begins 
execution

- a thread arriving at **CyclicBarrier** or **Exchanger** *happens-before* the other threads are 
releaased from that same barrier or exchange point
    - if **CyclicBarrier** uses a *barrier action*
        - arriving at the barrier *happens-before* the barrier action
        - the barrier-action *happens-before* threads are released from the barrier.
        - (Transitivity!)

