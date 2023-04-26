# 14.1 State Dependence

### Single-Threaded State Dependence
- if a state-based precondition isn't true when the method is called it will never be called
    - (i.e. "the connection pool is nonempty")
    
    
- classes in sequential/non-concurrent programs can be designed to fail if a precondition 
doesn't hold. 
    - EASY PEASY!
   
   
### Concurrent State Dependence
- state-based conditions can be modified through the actions of other threads
    - (i.e. a pool that was empty a few actions ago can become nonempty)
    
    
- waiting for the precondition to become true is a better alternative
    - (than failing when the precondition isn't true)
    - a state-dependent operation that blocks until the operation can succeed is more graceful than
    those that just fail
        - also less error-prone
        

- **condition queue** (built-in) mechanism allows threads to block until an object has
reached a state that allows a thread to make progress

#### Blocking State-Dependent Action

    EXAMPLE FLOW
    
        - acquire lock on object state
        - while (precondition doesn't hold) {
            - release lock
            - wait until precondition might hold
            - (optional) fail if interrupted or timeout expires
            - reacquire lock
        }
        - perform action
        - release lock
- Unusual Locking Pattern (lock is released and reacquired in the middle of the operation)
    - state vars that make up the precondition must be guarded by that lock in 
    order to remain constant while the precondition is tested
    - if the precondition doesn't hold
        - the lock has to be released so that another thread can modify 
        the object state
        - (otherwise the precondition will never become true)
    - the lock has to be reacquired before we can retest the precondition
    
### BoundedBuffers (Oh... these again)
- (Ex: **ArrayBlockingQueue**)
- commonly used in *producer-consumer* design.
- provides **put** and **take** operations that have 
preconditions
    - can't **take** from empty buffer. 
    - can't **put** to a full buffer
    
    
- state dependent operations can handle precondition failure:
    - throwing an exception/returning error status
        - (forces the caller to choose how to handle it)
    - blocking until object transitions to a progressable state
    
    
#### BaseBoundedBuffer
(See Examples.BaseBoundedBuffer)
- Base class used for further impl/approach of bounded buffer
- implements a classic "array-based" circular buffer
    - buffer state variables are guarded by object's intrinsic lock
        - buffer
        - head
        - tail
        - count
    - provides **doPut** and **doTake** methods that are used by
    subclasses to impl their own **put** and **take** operations
    - underlying state is hidden from subclasses. 
    
    
## 14.1.1 Example: Propagating precondition failure to callers.

    public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {
        
        public GrumpyGroundedBuffer(int size) {
            super(size);
        }
        
        public synchronized void put(V value) throws BufferFullException {
            if (isFull()) {
                throw new BufferFullException();
            }
            doPut(value);
        }
        
        public synchronized V take() throws BufferEmptyException {
            if (isEmpty()) {
                throw new BufferEmptyException();
            }
            return doTake();
        }
    }
### Explanation
- crude/naive impl (Easy to Implement)
    - **put/take** methods are **synchronized**
        - guarantees exclusive access to buffer's state
        - (required because both use *check-then-act* semantics)
    - forces caller to manage state dependence
        
        
- Downsides (Crappy To Use)
    - For a multi-threaded application, Full/Empty states aren't really 
    exceptions. They are valid states.
    - it is an anti-pattern to use exceptions as a means for flow control
    
        
        Client Logic example:
        
        while (true) {
            try {
                V item = buffer.take();
                // use item
                break;
            } catch (BufferEmptyException e) {
                Thread.sleep(SLEEP_GRANULARITY);
            }
        }
- Ugly Example due to the exception management.
- Alternative: (return an error when the buffer is in the wrong state)
    - PRO: 
        - the exception mechanism is slightly better
    - CON:
        - we are STILL forcing callers to deal w/ preconditions themselves
        
        

- **take()** tradeoff
    - example above retries w/ a sleep
    - CON:
        - can "oversleep" if bugger state changes very quickly after the call to *sleep*
        - (poor responsiveness) 
    - *busy/spin waiting* (retry immediately w/o sleeping)
    - CON: 
        - consumes a LOT of CPU time if buffer state doesn't change much
        
    
    There is tradeoff here between:
    
        1.) poor CPU usage of spinning
        2.) poor responsiveness of sleeping. 
        
    
    Somewhere between busy wait + sleep, there is usually a Thread.yield in each iteration
        - "hint" to a scheduler that it is a good time for another thread to run


    If you are blocked waiting for another thread to run:
        - "something might happen faster" if you yield the processor rather than chewing up
        your full scheduling quantum. 

#### Verdict
- Don't do this.
- the simplicity of the implementation isn't worth the complexity of actually 
using it
    - caller must be prepared to catch exceptions and retry for every buffer operation


- NOTE: pushing state dependence back to caller creates additional problems. 
    - We can't preserve FIFO ordering, because forcing caller to retry loses
    the "who arrived first" information.
    
#### Better Solutions than BoundedBuffer
- **Queue** supports the ideal scenario
    - Better Exception Handling
        - if queue is empty
            - **poll** returns *null*
            - **remove** throws an exception
    - (CON) -> not intended for *producer/consumer* designs


- **BlockingQueue**
    - same characteristics as **Queue**
    - better option for *producer/consumer* systems, because its operations
    will BLOCK until queue is in the right state to proceed.
    
## 14.1.2 - Example: Crude Blocking (via Polling and Sleeping)

    EXAMPLE
    
        BoundedBuffer using Crude Blocking
        
        public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {
        
            public SleepyBoundedBuffer(int size) {
                super(size);
            }
        
            public void put(V value) throws InterruptedException {
                
                while (true) {
                    synchronized (this) {
                        if (!isFull()) {
                            doPut(value);
                            return;
                        }
                    }
                    Thread.sleep(SLEEP_GRANULARITY);
                }
              
            }
            
            public V take() throws InterruptedException {
                
                while (true) {
                    synchronized (this) {
                        if (!isEmpty()) {
                            return do Take();
                        }
                    }
                    Thread.sleep(SLEEP_GRANULARITY);
                }
            }
        }
### Explanation
- In order to overcome the lame/inconvenient retry logic from the previous example
    - we encapsulate the same "poll and sleep" mechanism within the **put/take** operations
    - if buffer is empty:
        - take sleeps until an external actor places data in the buffer
    - if buffer is full:
        - put sleeps until an external actor makes room by consuming data from the buffer. 
- RESULT:
    - The approach simplifies the USE of the buffer
        - If operation can continue, it does, else it blocks
        - caller no longer deals w/ exception handling OR retry logic 
    - ENCAPSULATES precondition management. 
        - (The caller no longer has to manage the precondition/state)
        

- COMPLEXITY (increased)
    - buffer must test the state condition w/ the lock held because the vars that represent the
    state condition are SHARED (guarded by the buffer's intrinsic lock)
    - if test fails:
        - executing thread goes to sleep to allow other threads access to the buffer. 
       
       
       NOTE: 
        - Sleeping w/ a lock held is never a good idea. 
        - However, it is CRITICAL to this type of problem, because the desired precondition
        can't ever become true unless the lock is released for some external actor to 
        change the state. 
        
        
- OUTSTANDING PROBLEMS
    - Sleep Granularity Tradeoff still exists (See below)
    - caller must deal with **InterruptedException**
        - when any method blocks waiting for a condition to become progressive, the "polite"
        solution is to provide a cancellation mechanism (Chapter 7)
        
        
        A well-behaved method should support cancellation through interruption
            - return early and throw InterruptedException if interrupted. 
        
        
#### Sleep Granularity (Responsiveness vs. CPU Usage)
![Sleep Granularity](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/AdvancedTopics_4/BuildingCustomSynchronizers_14/Images/Screen Shot 2021-01-11 at 3.25.28 PM.png)
- (Shows an "oversleep" because the state condition
change occurred just after it went to sleep)
- There may be a delay between 
    - when buffer space becomes available again
    - when thread wakes up and checks again.


### Still Painful
- using *crude blocking* (polling and sleeping) is pretty painful, and we still don't
have a very good answer for the sleep granularity trade off. 


    Better: 
    - suspend a thread
    - ensure it is PROMPTLY awakened when the condition progresses to meet the needs of the
    precondition...
    
    
## 14.1.3 Condition Queues (to the rescue)

### Characteristics
- These do exactly what the previous suggestion ends with
- gives a group of threads (called a *wait set*) a way to wait for
a specific condition to become true

    
    Queue               ELEMENTS = data items
    Condition Queue     ELEMENTS = threads waiting for condition
    
### Objects as Locks, Condition Queues
- Each Java object can act as a(n) (intrinsic) lock
- Each Java object can also act as a(n) (intrinsic) *condition queue*
    - API consists of 
        - **wait**
        - **notify**
        - **notifyAll**
        
        
#### Relationship between object's intrinsic lock and intrinsic condition queue
- In order to call any of the condition queue methods on object X
    - you must hold the lock on object X.
    

- The mechanism for waiting for "state-based conditions" is NECESSARILY tightly bound to
the mechanism for "preserving state consistency"
    - you can't wait for a condition unless you can examine its state
    - you can't release another thread from a condition **wait** unless you can modify that state
    
    
- **Object.wait**
    - atomically releases the lock
    - requests that OS suspend current thread
        - (This allows other threads to acquire the lock and modify the object's state)
        - (This is the only way to set preconditions to allow threads to make progress)
    - suspended thread wakes up
        - reacquires lock
        - returns the result of its action
        
        
        wait                    I'm going to sleep, but wake me up if something 
                                cool happens
                                
                                
        notify/notifyAll        SHIT HAPPENED!


### BoundedBuffer Example (Using Condition Queues)

    public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {
        // CONDITION PREDICATE:     nonfull     (!isFull())
        // CONDITION PREDICATE:     nonempty    (!isEmpty())
        
        public BoundedBuffer(int size) {
            super(size);
        }
        
        // BLOCKS until not-full
        public synchronized void put(V value) throws InterruptedException {
            while(isFull()) {
                wait();
            }
            doPut(value);
            notifyAll();
        }
        
        // BLOCKS until non-empty
        public synchronized V take() throws InterruptedException {
            while(isEmpty()) {
                wait();
            }
            V value = doTake();
            notifyAll();
            return value;
        }
    
    }
#### Explanation
- BoundedBuffer implementation using **wait** and **notifyAll**


- PROS:
    - much simpler than the **crude blocking** impl
    - handles "sleep granularity better"
        - more efficient, 
            - because it wakes up less frequently if the buffer's state doesn't change. 
        - more responsive, 
            - wakes up PROMPTLY when a related state change occurs  


- semantics don't change. 
    - multidimensional optimization
        - CPU efficiency
        - context switch overhead
        - responsiveness. 
    - more efficient way to express/manage "state dependence"
    - does the SAME things that "sleep and polling does" 
        - EXCEPTION:
            - only a *fair condition queue* can gaurantee the relative order
            in which threads are released from the *wait set*
            - intrinsic *condition queues* (like intrinsic locks) do NOT
            offer *fair queueing*
            - explicit **Conditions** offer a choice between fair/nonfair queuing
                
        
### Result
- With exception to the fair/nonfair notes above, our **BoundedBuffer** is 
"production ready"
    - easy to use
    - manages state dependence
        - single notifications are a bit better than **notifyAll**
            - (See 14.3 **ConditionBoundedBuffer**)


- timed versions of **put/take** should also be included
    - this allows blocking operations to time out if they can't complete within a 
    given time budget
    - requires the use of the timed version of **Object.wait**